package dao.cacao.dnd5sheet.domain.use_case.document

import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import dao.cacao.dnd5sheet.domain.model.Document
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersHandbookDocumentUseCase @Inject constructor(
    private val documentRepository: DocumentRepository,
) {
    operator fun invoke(documentId: Long): Flow<Document> {
        return documentRepository.getDocument(
            documentId = documentId,
        )
    }
}