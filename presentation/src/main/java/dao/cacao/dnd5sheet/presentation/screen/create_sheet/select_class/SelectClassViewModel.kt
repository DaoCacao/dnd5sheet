package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectClassViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val classRepository: ClassRepository,
) : ViewModel() {

    private val args = SelectClassRoute.args(savedStateHandle)
    val state = MutableStateFlow(SelectClassState.loading())
    val event = MutableSharedFlow<SelectClassEvent>()

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
            event.emit(SelectClassEvent.NavigateToNext(sheetId = args.sheetId))
        }
    }

    fun onClassInfoClick(characterClass: CharacterClass) {
        viewModelScope.launch {
            event.emit(SelectClassEvent.NavigateToDocument(documentId = characterClass.documentId))
        }
    }
}