package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateAbilityModifierUseCase
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateSkillModifierUseCase
import dao.cacao.dnd5sheet.presentation.base.BaseViewModel
import dao.cacao.dnd5sheet.presentation.router.Routes
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sheetRepository: SheetRepository,
    private val abilityRepository: AbilityRepository,
    private val skillRepository: SkillRepository,
    private val calculateAbilityModifierUseCase: CalculateAbilityModifierUseCase,
    private val calculateSkillModifierUseCase: CalculateSkillModifierUseCase,
) : BaseViewModel() {

    private val sheetId = savedStateHandle.get<Long>(Routes.argSheetId) ?: error("Required argument")

    private var level: Int = 0

    var state by mutableStateOf<SheetState>(SheetState.Loading)
        private set

    init {
        viewModelScope.launch {
            val sheet = sheetRepository.getSheet(sheetId).first()
            state = SheetState.Content(
                level = sheet.level ?: 0,
                characterName = sheet.characterName ?: "",
                characterRace = sheet.characterRace ?: "",
                characterClass = sheet.characterClass ?: "",
                proficiencyBonus = sheet.proficiencyBonus ?: 0,
                abilities = sheet.abilities.map { ability ->
                    SheetState.Content.Ability(
                        id = ability.id,
                        name = ability.name,
                        score = ability.score,
                        modifier = calculateAbilityModifierUseCase(ability.score),
                    )
                },
                skills = sheet.skills.map { skill ->
                    val ability = sheet.abilities.first { it.id == skill.abilityId }
                    SheetState.Content.Skill(
                        id = skill.id,
                        abilityId = ability.id,
                        name = skill.name,
                        ability = ability.name,
                        modifier = calculateSkillModifierUseCase(ability.score, sheet.proficiencyBonus ?: 0, skill.proficiency),
                        proficiency = skill.proficiency,
                    )
                },
            )
        }
    }

    fun onLevelChange(level: Int) {
//        this.level = level
        state = state.ifContent { it.copy(level = level) }
        viewModelScope.launch {
            sheetRepository.updateLevel(sheetId, level)
        }
    }

    fun onCharacterNameChange(characterName: String) {
        state = state.ifContent { it.copy(characterName = characterName) }
        viewModelScope.launch {
            sheetRepository.updateCharacterName(sheetId, characterName)
        }
    }

    fun onCharacterRaceChange(characterRace: String) {
        state = state.ifContent { it.copy(characterRace = characterRace) }
        viewModelScope.launch {
            sheetRepository.updateCharacterRace(sheetId, characterRace)
        }
    }

    fun onCharacterClassChange(characterClass: String) {
        state = state.ifContent { it.copy(characterClass = characterClass) }
        viewModelScope.launch {
            sheetRepository.updateCharacterClass(sheetId, characterClass)
        }
    }

    // update proficiency bonus
    // update depending skill modifiers
    fun onProficiencyBonusChange(proficiencyBonus: Int) {
        state = state.ifContent {
            it.copy(
                proficiencyBonus = proficiencyBonus,
                skills = it.skills.update({ it.proficiency }) { skill ->
                    val abilityScore = it.abilities.first { it.id == skill.abilityId }.score
                    skill.copy(
                        modifier = calculateSkillModifierUseCase(abilityScore, proficiencyBonus, skill.proficiency)
                    )
                }
            )
        }
        viewModelScope.launch {
            sheetRepository.updateProficiencyBonus(sheetId, proficiencyBonus)
        }
    }

    // update ability score
    // update ability modifier
    // update depending skill modifiers
    fun onAbilityScoreChange(abilityId: Long, score: Int) {
        state = state.ifContent {
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
        state = state.ifContent {
            it.copy(
                skills = it.skills.update({ it.id == skillId }) { skill ->
                    val abilityScore = it.abilities.first { it.id == skill.abilityId }.score
                    skill.copy(
                        proficiency = proficiency,
                        modifier = calculateSkillModifierUseCase(abilityScore, it.proficiencyBonus, proficiency),
                    )
                },
            )
        }
        viewModelScope.launch {
            skillRepository.updateSkillProficiency(skillId, proficiency)
        }
    }

    private fun SheetState.ifContent(update: (SheetState.Content) -> SheetState): SheetState {
        return if (this is SheetState.Content) update(this) else this
    }

    private fun <T> List<T>.update(predicate: (T) -> Boolean, update: (T) -> T): List<T> {
        return map { if (predicate(it)) update(it) else it }
    }
}