package dao.cacao.dnd5sheet.data.storage.local

import dao.cacao.dnd5sheet.data.mapper.DocumentMapper
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.model.Document
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DocumentLocalStorage @Inject constructor(
    private val database: AppDatabase,
    private val documentMapper: DocumentMapper,
) {
    fun getDocument(documentId: Long): Flow<Document> {
        return database.documentDao().getById(documentId)
            .map(documentMapper::map)
    }
}