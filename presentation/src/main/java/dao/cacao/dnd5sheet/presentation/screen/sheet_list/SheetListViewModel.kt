package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetListViewModel @Inject constructor(
    private val sheetRepository: SheetRepository,
) : BaseViewModel<SheetListState, SheetListEvent>(SheetListState.loading()) {

    init {
        viewModelScope.launch {
            sheetRepository
                .getSheets()
                .collectLatest { list ->
                    state.update {
                        SheetListState.content(
                            items = list.map { it.map() },
                        )
                    }
                }
        }
    }

    fun onSheetClick(sheetId: Long) {
        viewModelScope.launch {
            event.emit(SheetListEvent.NavigateToSheet(sheetId))
        }
    }

    fun onCreateNewSheetClick() {
        viewModelScope.launch {
            val sheet = sheetRepository.createSheet()
            event.emit(SheetListEvent.NavigateToCreateSheet(sheet.id))
        }
    }

    fun onDeleteSheetClick(sheetId: Long) {
        viewModelScope.launch {
            sheetRepository.deleteSheet(sheetId)
        }
    }

    private fun Sheet.map() = SheetListState.Item(
        id = id,
        level = character.level ?: 0,
        characterName = character.characterName ?: "",
        characterRace = characterRace?.name ?: "",
        characterClass = characterClass?.name ?: "",
    )
}