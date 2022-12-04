package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.presentation.base.BaseViewModel
import dao.cacao.dnd5sheet.presentation.router.Routes
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sheetRepository: SheetRepository,
) : BaseViewModel() {

    private val sheetId = savedStateHandle.get<Long>(Routes.argSheetId) ?: error("Required argument")

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
                strength = sheet.strength ?: 0,
                dexterity = sheet.dexterity ?: 0,
                constitution = sheet.constitution ?: 0,
                intelligence = sheet.intelligence ?: 0,
                wisdom = sheet.wisdom ?: 0,
                charisma = sheet.charisma ?: 0,
            )
        }
    }

    fun onLevelChange(level: Int) {
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

    fun onStrengthChange(value: Int) {
        state = state.ifContent { it.copy(strength = value) }
        viewModelScope.launch {
            sheetRepository.updateStrength(sheetId, value)
        }
    }

    fun onDexterityChange(value: Int) {
        state = state.ifContent { it.copy(dexterity = value) }
        viewModelScope.launch {
            sheetRepository.updateDexterity(sheetId, value)
        }
    }

    fun onConstitutionChange(value: Int) {
        state = state.ifContent { it.copy(constitution = value) }
        viewModelScope.launch {
            sheetRepository.updateConstitution(sheetId, value)
        }
    }

    fun onIntelligenceChange(value: Int) {
        state = state.ifContent { it.copy(intelligence = value) }
        viewModelScope.launch {
            sheetRepository.updateIntelligence(sheetId, value)
        }
    }

    fun onWisdomChange(value: Int) {
        state = state.ifContent { it.copy(wisdom = value) }
        viewModelScope.launch {
            sheetRepository.updateWisdom(sheetId, value)
        }
    }

    fun onCharismaChange(value: Int) {
        state = state.ifContent { it.copy(charisma = value) }
        viewModelScope.launch {
            sheetRepository.updateCharisma(sheetId, value)
        }
    }

    private fun SheetState.ifContent(update: (SheetState.Content) -> SheetState): SheetState {
        return if (this is SheetState.Content) update(this) else this
    }
}