package dao.cacao.dnd5sheet.presentation.screen.document

import dao.cacao.dnd5sheet.domain.model.Document

sealed class DocumentState {
    object Loading : DocumentState()
    data class Content(
        val document: Document,
    ) : DocumentState()
}