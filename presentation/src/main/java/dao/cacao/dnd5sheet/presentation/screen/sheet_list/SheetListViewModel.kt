package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.domain.use_case.sheet.CreateSheetUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.DeleteSheetUseCase
import dao.cacao.dnd5sheet.domain.use_case.sheet.GetSheetsUseCase
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetListViewModel @Inject constructor(
    private val getSheetsUseCase: GetSheetsUseCase,
    private val createSheetUseCase: CreateSheetUseCase,
    private val deleteSheetUseCase: DeleteSheetUseCase,
) : ViewModel() {

    val state = state(SheetList.State(isLoading = true))
    val event = event<SheetList.Event>()

    init {
        viewModelScope.launch {
            getSheetsUseCase().collectLatest { list ->
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
            val sheet = createSheetUseCase()
            event.emit(SheetList.Event.NavigateToCreateSheet(sheet.id))
        }
    }

    fun onDeleteSheetClick(sheetId: Long) {
        viewModelScope.launch {
            deleteSheetUseCase(sheetId)
        }
    }

    private fun Sheet.map() = SheetList.State.Item(
        id = id,
        level = level ?: 0,
        characterName = characterName ?: "",
        characterRace = characterSubrace?.name ?: characterRace?.name ?: "",
        characterClass = characterClass?.name ?: "",
    )
}