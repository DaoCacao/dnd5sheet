package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.CharacterRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateAbilityModifierUseCase
import dao.cacao.dnd5sheet.domain.use_case.calculation.CalculateSkillModifierUseCase
import kotlinx.coroutines.flow.first
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

    private val args = SheetRoute.args(savedStateHandle)

    var isLoading by mutableStateOf(true)
    var level by mutableStateOf(0)
    var characterName by mutableStateOf("")
    var characterRace by mutableStateOf("")
    var characterClass by mutableStateOf("")
    var proficiencyBonus by mutableStateOf(0)
    var abilities by mutableStateOf<List<Ability>>(emptyList())
    var skills by mutableStateOf<List<Skill>>(emptyList())

    init {
        viewModelScope.launch {
            val sheet = sheetRepository.getSheet(args.sheetId).first()
            isLoading = false
            level = sheet.character.level ?: 0
            characterName = sheet.character.characterName ?: ""
            characterRace = sheet.character.characterRace ?: ""
            characterClass = sheet.character.characterClass ?: ""
            proficiencyBonus = sheet.character.proficiencyBonus ?: 0
            abilities = sheet.abilities.map { ability ->
                Ability(
                    id = ability.id,
                    name = ability.name,
                    score = ability.score,
                    modifier = calculateAbilityModifierUseCase(ability.score),
                )
            }
            skills = sheet.skills.map { skill ->
                val ability = sheet.abilities.first { it.id == skill.abilityId }
                Skill(
                    id = skill.id,
                    abilityId = ability.id,
                    name = skill.name,
                    ability = ability.name,
                    modifier = calculateSkillModifierUseCase(ability.score, sheet.character.proficiencyBonus ?: 0, skill.proficiency),
                    proficiency = skill.proficiency,
                )
            }
        }
    }

    fun onLevelChange(level: Int) {
        this.level = level
        viewModelScope.launch {
            characterRepository.updateLevel(args.sheetId, level)
        }
    }

    fun onCharacterNameChange(characterName: String) {
        this.characterName = characterName
        viewModelScope.launch {
            characterRepository.updateCharacterName(args.sheetId, characterName)
        }
    }

    fun onCharacterRaceChange(characterRace: String) {
        this.characterRace = characterRace
        viewModelScope.launch {
            characterRepository.updateCharacterRace(args.sheetId, characterRace)
        }
    }

    fun onCharacterClassChange(characterClass: String) {
        this.characterClass = characterClass
        viewModelScope.launch {
            characterRepository.updateCharacterClass(args.sheetId, characterClass)
        }
    }

    // update proficiency bonus
    // update depending skill modifiers
    fun onProficiencyBonusChange(proficiencyBonus: Int) {
        this.proficiencyBonus = proficiencyBonus
        this.skills = skills.update({ it.proficiency }) { skill ->
            val abilityScore = abilities.first { it.id == skill.abilityId }.score
            skill.copy(
                modifier = calculateSkillModifierUseCase(abilityScore, proficiencyBonus, skill.proficiency)
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
        this.abilities = abilities.update({ it.id == abilityId }) { ability ->
            ability.copy(
                score = score,
                modifier = calculateAbilityModifierUseCase(score),
            )
        }
        this.skills = skills.update({ it.abilityId == abilityId }) { skill ->
            skill.copy(
                modifier = calculateSkillModifierUseCase(score, proficiencyBonus, skill.proficiency)
            )
        }
        viewModelScope.launch {
            abilityRepository.updateAbilityScore(abilityId, score)
        }
    }

    // update skill proficiency
    // update skill modifier
    fun onSkillProficiencyChange(skillId: Long, proficiency: Boolean) {
        this.skills = skills.update({ it.id == skillId }) { skill ->
            val abilityScore = abilities.first { it.id == skill.abilityId }.score
            skill.copy(
                proficiency = proficiency,
                modifier = calculateSkillModifierUseCase(abilityScore, proficiencyBonus, proficiency),
            )
        }
        viewModelScope.launch {
            skillRepository.updateSkillProficiency(skillId, proficiency)
        }
    }

    private fun <T> List<T>.update(predicate: (T) -> Boolean, update: (T) -> T): List<T> {
        return map { if (predicate(it)) update(it) else it }
    }
}

data class Ability(
    val id: Long,
    val name: String,
    val score: Int,
    val modifier: Int?,
)

data class Skill(
    val id: Long,
    val abilityId: Long,
    val name: String,
    val ability: String,
    val modifier: Int?,
    val proficiency: Boolean,
)