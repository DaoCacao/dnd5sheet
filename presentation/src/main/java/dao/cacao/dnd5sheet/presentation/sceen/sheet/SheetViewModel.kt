package dao.cacao.dnd5sheet.presentation.sceen.sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.use_case.sheet.GetSheetUseCase
import dao.cacao.dnd5sheet.presentation.router.Routes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSheetUseCase: GetSheetUseCase,
) : ViewModel() {

    private val sheetId = savedStateHandle.get<Long>(Routes.argSheetId) ?: error("Required argument")

    var state by mutableStateOf<SheetState>(SheetState.Loading)
        private set

    init {
        viewModelScope.launch {
            getSheetUseCase.invoke(sheetId).collectLatest {
                state = SheetState.Content(
                    sheet = it,
                )
            }
        }
    }
}