package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_subrace

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.model.Subrace
import dao.cacao.dnd5sheet.domain.use_case.sheet.UpdateSubraceUseCase
import dao.cacao.dnd5sheet.domain.use_case.subrace.GetPlayersHandbookSubracesUseCase
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectSubraceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPlayersHandbookSubracesUseCase: GetPlayersHandbookSubracesUseCase,
    private val updateSubraceUseCase: UpdateSubraceUseCase,
) : ViewModel() {

    val args = args(SelectSubraceRoute, savedStateHandle)
    val state = state(SelectSubrace.State(isLoading = true))
    val event = event<SelectSubrace.Event>()

    init {
        viewModelScope.launch {
            val races = getPlayersHandbookSubracesUseCase(args.raceId).first()
            state.update {
                it.copy(
                    isLoading = false,
                    races = races,
                )
            }
        }
    }

    fun onSubraceClick(subrace: Subrace) {
        viewModelScope.launch {
            updateSubraceUseCase(sheetId = args.sheetId, subraceId = subrace.id)
            if (args.popBackStack)
                event.emit(SelectSubrace.Event.NavigateBack)
            else
                event.emit(SelectSubrace.Event.NavigateToNext(sheetId = args.sheetId))
        }
    }

    fun onSubraceInfoClick(subrace: Subrace) {
        viewModelScope.launch {
            event.emit(SelectSubrace.Event.NavigateToDocument(documentId = 0))
        }
    }
}