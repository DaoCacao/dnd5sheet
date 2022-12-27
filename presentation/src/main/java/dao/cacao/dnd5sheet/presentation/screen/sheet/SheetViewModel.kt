package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.CharacterRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.model.Ability
import dao.cacao.dnd5sheet.domain.model.Skill
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateAbilityModifierUseCase
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateSkillModifierUseCase
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sheetRepository: SheetRepository,
    private val characterRepository: CharacterRepository,
    private val abilityRepository: AbilityRepository,
    private val skillRepository: SkillRepository,
    private val calculateAbilityModifierUseCase: CalculateAbilityModifierUseCase,
    private val calculateSkillModifierUseCase: CalculateSkillModifierUseCase,
) : ViewModel() {

    val args = args(SheetRoute, savedStateHandle)
    val state = state(Sheet.State(isLoading = true))
    val event = event<Sheet.Event>()

    init {
        viewModelScope.launch {
            val sheet = sheetRepository.getSheet(args.sheetId).first()
            state.update {
                it.copy(
                    isLoading = false,
                    name = sheet.character.characterName ?: "",
                    characterRace = sheet.characterRace?.name ?: "",
                    characterClass = sheet.characterClass?.name ?: "",
                    level = sheet.character.level ?: 0,
                    proficiencyBonus = sheet.character.proficiencyBonus ?: 0,
                    abilities = sheet.abilities.map { it.map() },
                    skills = sheet.skills.map {
                        it.map(
                            sheet.abilities.first { ability -> ability.id == it.abilityId },
                            sheet.character.proficiencyBonus,
                        )
                    }
                )
            }
        }
    }

    fun onPageChange(page: Sheet.State.SheetScreenPages) {
        state.update { it.copy(page = page) }
    }

    fun onLevelChange(level: Int) {
        state.update { it.copy(level = level) }
        viewModelScope.launch {
            characterRepository.updateLevel(args.sheetId, level)
        }
    }

    fun onCharacterNameChange(characterName: String) {
        state.update { it.copy(name = characterName) }
        viewModelScope.launch {
            characterRepository.updateCharacterName(args.sheetId, characterName)
        }
    }

    fun onCharacterRaceClick() {
        viewModelScope.launch {
            event.emit(Sheet.Event.NavigateToSelectRace(args.sheetId))
        }
    }

    fun onCharacterClassClick() {
        viewModelScope.launch {
            event.emit(Sheet.Event.NavigateToSelectClass(args.sheetId))
        }
    }

    // update proficiency bonus
    // update depending skill modifiers
    fun onProficiencyBonusChange(proficiencyBonus: Int) {
        state.update {
            it.copy(
                proficiencyBonus = proficiencyBonus,
                skills = it.skills.update({ it.proficiency }) { skill ->
                    val abilityScore = it.abilities.first { it.id == skill.abilityId }.score
                    skill.copy(
                        modifier = calculateSkillModifierUseCase(abilityScore, proficiencyBonus, skill.proficiency)
                    )
                },
            )
        }
        viewModelScope.launch {
            characterRepository.updateProficiencyBonus(args.sheetId, proficiencyBonus)
        }
    }

    // update ability score
    // update ability modifier
    // update depending skill modifiers
    fun onAbilityScoreChange(abilityId: Long, score: Int) {
        state.update {
            it.copy(
                abilities = it.abilities.update({ it.id == abilityId }) { ability ->
                    ability.copy(
                        score = score,
                        modifier = calculateAbilityModifierUseCase(score),
                    )
                },
                skills = it.skills.update({ it.abilityId == abilityId }) { skill ->
                    skill.copy(
                        modifier = calculateSkillModifierUseCase(score, it.proficiencyBonus, skill.proficiency)
                    )
                }
            )
        }
        viewModelScope.launch {
            abilityRepository.updateAbilityScore(abilityId, score)
        }
    }

    // update skill proficiency
    // update skill modifier
    fun onSkillProficiencyChange(skillId: Long, proficiency: Boolean) {
        state.update {
            it.copy(
                skills = it.skills.update({ it.id == skillId }) { skill ->
                    val abilityScore = it.abilities.first { it.id == skill.abilityId }.score
                    skill.copy(
                        proficiency = proficiency,
                        modifier = calculateSkillModifierUseCase(abilityScore, it.proficiencyBonus, proficiency),
                    )
                }
            )
        }
        viewModelScope.launch {
            skillRepository.updateSkillProficiency(skillId, proficiency)
        }
    }

    private fun <T> List<T>.update(predicate: (T) -> Boolean, update: (T) -> T): List<T> {
        return map { if (predicate(it)) update(it) else it }
    }

    private fun Ability.map() = Sheet.State.AbilityItem(
        id = id,
        name = name,
        score = score,
        modifier = calculateAbilityModifierUseCase(score),
    )

    private fun Skill.map(ability: Ability, proficiencyBonus: Int?) = Sheet.State.SkillItem(
        id = id,
        abilityId = ability.id,
        name = name,
        abilityName = ability.name,
        modifier = calculateSkillModifierUseCase(
            ability.score,
            proficiencyBonus ?: 0,
            proficiency,
        ),
        proficiency = proficiency,
    )
}