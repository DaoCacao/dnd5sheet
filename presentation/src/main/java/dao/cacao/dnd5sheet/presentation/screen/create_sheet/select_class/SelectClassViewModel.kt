package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectClassViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val classRepository: ClassRepository,
) : ViewModel() {

    val args = args(SelectClassRoute, savedStateHandle)
    val state = state(SelectClass.State(isLoading = true))
    val event = event<SelectClass.Event>()

    init {
        viewModelScope.launch {
            val classes = classRepository.getClasses().first()
            state.update {
                it.copy(
                    isLoading = false,
                    classes = classes,
                )
            }
        }
    }

    fun onClassClick(characterClass: CharacterClass) {
        viewModelScope.launch {
            classRepository.updateCharacterClass(sheetId = args.sheetId, classId = characterClass.id)
            if (args.popBackStack)
                event.emit(SelectClass.Event.NavigateBack)
            else
                event.emit(SelectClass.Event.NavigateToNext(sheetId = args.sheetId))
        }
    }

    fun onClassInfoClick(characterClass: CharacterClass) {
        viewModelScope.launch {
            event.emit(SelectClass.Event.NavigateToDocument(documentId = characterClass.documentId))
        }
    }
}