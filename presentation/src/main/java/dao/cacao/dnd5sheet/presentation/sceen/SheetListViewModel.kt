package dao.cacao.dnd5sheet.presentation.sceen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.domain.use_case.CreateSheetUseCase
import dao.cacao.dnd5sheet.domain.use_case.DeleteSheetUseCase
import dao.cacao.dnd5sheet.domain.use_case.GetSheetsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetListViewModel @Inject constructor(
    private val getSheetsUseCase: GetSheetsUseCase,
    private val createSheetUseCase: CreateSheetUseCase,
    private val deleteSheetUseCase: DeleteSheetUseCase,
) : ViewModel() {

    var state by mutableStateOf<List<Sheet>?>(null)
        private set

    init {
        viewModelScope.launch {
            getSheetsUseCase.invoke().collect {
                state = it
            }
        }
    }

    fun onCreateNewSheetClick() {
        viewModelScope.launch {
            createSheetUseCase.invoke()
        }
    }

    fun onSheetClick(sheetId: Long) {
    }

    fun onDeleteSheetClick(sheetId: Long) {
        viewModelScope.launch {
            deleteSheetUseCase.invoke(sheetId)
        }
    }
}