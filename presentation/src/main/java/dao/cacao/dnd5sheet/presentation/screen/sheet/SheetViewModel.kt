package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
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
    private val abilityRepository: AbilityRepository,
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
                abilities = sheet.abilities.map {
                    SheetState.Content.Ability(
                        id = it.id,
                        name = it.name,
                        value = it.value,
                    )
                },
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

    fun onAbilityChange(abilityId: Long, value: Int) {
        state = state.ifContent {
            it.copy(
                abilities = it.abilities.map {
                    if (it.id == abilityId) it.copy(value = value) else it
                },
            )
        }
        viewModelScope.launch {
            abilityRepository.updateAbility(abilityId, value)
        }
    }

    private fun SheetState.ifContent(update: (SheetState.Content) -> SheetState): SheetState {
        return if (this is SheetState.Content) update(this) else this
    }
}