package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.CharacterRepository
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectNameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    val args = args(SelectNameRoute, savedStateHandle)
    val state = state(SelectName.State(isLoading = true))
    val event = event<SelectName.Event>()

    init {
        state.update {
            it.copy(
                isLoading = false,
                name = args.name,
            )
        }
    }

    fun onNameChange(name: String) {
        state.update { it.copy(name = name) }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            characterRepository.updateCharacterName(
                characterId = args.sheetId,
                characterName = state.value.name,
            )
            if (args.popBackStack)
                event.emit(SelectName.Event.NavigateBack)
            else
                event.emit(SelectName.Event.NavigateToNext)
        }
    }
}