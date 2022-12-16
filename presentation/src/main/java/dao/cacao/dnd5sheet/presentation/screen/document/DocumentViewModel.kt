package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import dao.cacao.dnd5sheet.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val documentRepository: DocumentRepository,
) : BaseViewModel<DocumentState, Unit>(DocumentState.loading()) {

    private val args = DocumentRoute.args(savedStateHandle)

    init {
        viewModelScope.launch {
            try {
                val document = documentRepository.getDocument(args.documentId).first()
                state.update {
                    DocumentState.content(
                        name = document.name,
                        text = document.text,
                    )
                }
            } catch (e: Exception) {
                state.update {
                    DocumentState.error()
                }
            }
        }
    }
}