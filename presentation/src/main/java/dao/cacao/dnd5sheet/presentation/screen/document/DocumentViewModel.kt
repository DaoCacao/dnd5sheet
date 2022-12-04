package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import dao.cacao.dnd5sheet.presentation.router.Routes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val documentRepository: DocumentRepository,
) : ViewModel() {

    private val documentId = savedStateHandle.get<Long>(Routes.argDocumentId) ?: error("Required argument")
    var state by mutableStateOf<DocumentState>(DocumentState.Loading)
        private set

    init {
        viewModelScope.launch {
            documentRepository.getDocument(documentId).collectLatest {
                state = DocumentState.Content(
                    document = it,
                )
            }
        }
    }
}