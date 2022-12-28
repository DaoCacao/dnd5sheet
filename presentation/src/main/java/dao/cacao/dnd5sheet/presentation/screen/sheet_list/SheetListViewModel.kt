package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetListViewModel @Inject constructor(
    private val sheetRepository: SheetRepository,
) : ViewModel() {

    val state = state(SheetList.State(isLoading = true))
    val event = event<SheetList.Event>()

    init {
        viewModelScope.launch {
            sheetRepository
                .getSheets()
                .collectLatest { list ->
                    state.update {
                        it.copy(
                            isLoading = false,
                            items = list.map { it.map() },
                        )
                    }
                }
        }
    }

    fun onSheetClick(sheetId: Long) {
        viewModelScope.launch {
            event.emit(SheetList.Event.NavigateToSheet(sheetId))
        }
    }

    fun onCreateNewSheetClick() {
        viewModelScope.launch {
            val sheet = sheetRepository.createSheet()
            event.emit(SheetList.Event.NavigateToCreateSheet(sheet.id))
        }
    }

    fun onDeleteSheetClick(sheetId: Long) {
        viewModelScope.launch {
            sheetRepository.deleteSheet(sheetId)
        }
    }

    private fun Sheet.map() = SheetList.State.Item(
        id = id,
        level = level ?: 0,
        characterName = characterName ?: "",
        characterRace = characterRace?.name ?: "",
        characterClass = characterClass?.name ?: "",
    )
}