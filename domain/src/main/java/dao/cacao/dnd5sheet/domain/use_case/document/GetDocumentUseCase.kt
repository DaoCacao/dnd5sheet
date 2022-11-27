package dao.cacao.dnd5sheet.domain.use_case.document

import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import dao.cacao.dnd5sheet.domain.model.Document
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDocumentUseCase @Inject constructor(
    private val documentRepository: DocumentRepository,
) : (Long) -> Flow<Document> {
    override fun invoke(documentId: Long) = documentRepository.getDocument(documentId)
}