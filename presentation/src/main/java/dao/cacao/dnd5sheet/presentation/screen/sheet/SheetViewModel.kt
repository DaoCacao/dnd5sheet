package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.model.AbilityWithScore
import dao.cacao.dnd5sheet.domain.model.Skill
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateAbilityModifierUseCase
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateSkillModifierUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.GetSheetUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.UpdateAbilityScoreUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.UpdateLevelUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.UpdateProficiencyBonusUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.UpdateSkillProficiencyUseCase
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSheetUseCase: GetSheetUseCase,
    private val updateLevelUseCase: UpdateLevelUseCase,
    private val updateProficiencyBonusUseCase: UpdateProficiencyBonusUseCase,
    private val updateAbilityScoreUseCase: UpdateAbilityScoreUseCase,
    private val updateSkillProficiencyUseCase: UpdateSkillProficiencyUseCase,

    private val calculateAbilityModifierUseCase: CalculateAbilityModifierUseCase,
    private val calculateSkillModifierUseCase: CalculateSkillModifierUseCase,
) : ViewModel() {

    val args = args(SheetRoute, savedStateHandle)
    val state = state(Sheet.State(isLoading = true))
    val event = event<Sheet.Event>()

    init {
        viewModelScope.launch {
            getSheetUseCase(args.sheetId).collectLatest { sheet ->
                state.update {
                    it.copy(
                        isLoading = false,
                        name = sheet.characterName ?: "",
                        raceId = sheet.characterRace?.id ?: "",
                        subRaceId = sheet.characterSubrace?.id ?: "",
                        classId = sheet.characterClass?.id ?: "",
                        characterRace = sheet.characterRace?.name ?: "",
                        characterSubrace = sheet.characterSubrace?.name ?: "",
                        characterClass = sheet.characterClass?.name ?: "",
                        level = sheet.level ?: 0,
                        proficiencyBonus = sheet.proficiencyBonus ?: 0,
                        abilities = emptyList(), // sheet.abilities.map { it.map() },
                        skills = emptyList() // sheet.skills.map {
                        // it.map(
                        //        sheet.abilities.first { ability -> ability.id == it.abilityId },
                        //        sheet.character.proficiencyBonus,
                        //    )
                        //}
                    )
                }
            }
        }
    }

    fun onPageChange(page: Sheet.State.SheetScreenPages) {
        state.update { it.copy(page = page) }
    }

    fun onLevelChange(level: Int) {
        state.update { it.copy(level = level) }
        viewModelScope.launch {
            updateLevelUseCase(args.sheetId, level)
        }
    }

    fun onCharacterNameClick() {
        viewModelScope.launch {
            event.emit(Sheet.Event.NavigateToSelectName(args.sheetId, state.value.name))
        }
    }

    fun onCharacterRaceClick() {
        viewModelScope.launch {
            event.emit(Sheet.Event.NavigateToSelectRace(
                sheetId = args.sheetId,
                raceId = state.value.raceId,
            ))
        }
    }

    fun onCharacterSubraceClick() {
        viewModelScope.launch {
            event.emit(Sheet.Event.NavigateToSelectSubrace(
                sheetId = args.sheetId,
                raceId = state.value.raceId,
                subRaceId = state.value.subRaceId,
            ))
        }
    }

    fun onCharacterClassClick() {
        viewModelScope.launch {
            event.emit(Sheet.Event.NavigateToSelectClass(
                sheetId = args.sheetId,
                classId = state.value.classId,
            ))
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
//            updateProficiencyBonusUseCase(args.sheetId, proficiencyBonus)
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
//            updateAbilityScoreUseCase(args.sheetId, abilityId, score)
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
//            updateSkillProficiencyUseCase(skillId, proficiency)
        }
    }

    private fun <T> List<T>.update(predicate: (T) -> Boolean, update: (T) -> T): List<T> {
        return map { if (predicate(it)) update(it) else it }
    }

    private fun AbilityWithScore.map() = Sheet.State.AbilityItem(
        id = id,
        name = name,
        score = score,
        modifier = calculateAbilityModifierUseCase(score),
    )

    private fun Skill.map(ability: AbilityWithScore, proficiencyBonus: Int?) = Sheet.State.SkillItem(
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