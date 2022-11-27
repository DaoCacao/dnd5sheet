package dao.cacao.dnd5sheet.presentation.sceen.sheet_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetListViewModel @Inject constructor(
    private val sheetRepository: SheetRepository,
) : ViewModel() {

    var state by mutableStateOf<SheetListState>(SheetListState.Loading)
        private set

    init {
        viewModelScope.launch {
            sheetRepository.getSheets().collectLatest {
                state = when {
                    it.isEmpty() -> SheetListState.Empty
                    else -> SheetListState.Content(
                        sheets = it,
                    )
                }
            }
        }
    }

    fun onCreateNewSheetClick() {
        viewModelScope.launch {
            sheetRepository.createSheet()
        }
    }

    fun onDeleteSheetClick(sheet: Sheet) {
        viewModelScope.launch {
            sheetRepository.deleteSheet(sheet.id)
        }
    }
}