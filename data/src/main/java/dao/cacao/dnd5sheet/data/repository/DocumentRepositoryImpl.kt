package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.DocumentLocalStorage
import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val documentLocalStorage: DocumentLocalStorage,
) : DocumentRepository {
    override fun getDocument(documentId: Long) = documentLocalStorage.getDocument(documentId)
}