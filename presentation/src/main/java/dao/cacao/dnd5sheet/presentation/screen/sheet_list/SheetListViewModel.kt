package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.use_case.CreateNewSheetUseCase
import dao.cacao.dnd5sheet.domain.use_case.DeleteSheetUseCase
import dao.cacao.dnd5sheet.presentation.base.BaseViewModel
import dao.cacao.dnd5sheet.presentation.router.Routes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetListViewModel @Inject constructor(
    private val sheetRepository: SheetRepository,
    private val createNewSheetUseCase: CreateNewSheetUseCase,
    private val deleteSheetUseCase: DeleteSheetUseCase,
) : BaseViewModel() {

    var state by mutableStateOf<SheetListState>(SheetListState.Loading)
        private set

    init {
        viewModelScope.launch {
            sheetRepository.getSheets().collectLatest {
                state = when {
                    it.isEmpty() -> SheetListState.Empty
                    else -> SheetListState.Content(
                        items = it.map {
                            SheetListState.Content.Item(
                                id = it.id,
                                level = it.level ?: 0,
                                characterName = it.characterName ?: "",
                                characterRace = it.characterRace ?: "",
                                characterClass = it.characterClass ?: "",
                            )
                        },
                    )
                }
            }
        }
    }

    fun onCreateNewSheetClick() {
        viewModelScope.launch {
            val sheetId = createNewSheetUseCase()
            navigateTo(Routes.sheetRoute(sheetId))
        }
    }

    fun onDeleteSheetClick(sheetId: Long) {
        viewModelScope.launch {
            deleteSheetUseCase(sheetId)
        }
    }

    fun onSheetClick(sheetId: Long) {
        navigateTo(Routes.sheetRoute(sheetId))
    }
}