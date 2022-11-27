package dao.cacao.dnd5sheet.presentation.sceen.document

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.use_case.document.GetDocumentUseCase
import dao.cacao.dnd5sheet.presentation.router.Routes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDocumentUseCase: GetDocumentUseCase,
) : ViewModel() {

    private val documentId = savedStateHandle.get<Long>(Routes.argDocumentId) ?: error("Required argument")
    var state by mutableStateOf<DocumentState>(DocumentState.Loading)
        private set

    init {
        viewModelScope.launch {
            getDocumentUseCase.invoke(documentId).collectLatest {
                state = DocumentState.Content(
                    document = it,
                )
            }
        }
    }
}