package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.use_case.document.GetPlayersHandbookDocumentUseCase
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPlayersHandbookDocumentUseCase: GetPlayersHandbookDocumentUseCase,
) : ViewModel() {

    val args = args(DocumentRoute, savedStateHandle)
    val state = state(Document.State(isLoading = true))

    init {
        viewModelScope.launch {
            try {
                val document = getPlayersHandbookDocumentUseCase(args.documentId).first()
                state.update {
                    it.copy(
                        isLoading = false,
                        name = document.name,
                        text = document.text,
                    )
                }
            } catch (e: Exception) {
                state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }
    }
}