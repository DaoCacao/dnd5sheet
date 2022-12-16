package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import dao.cacao.dnd5sheet.domain.model.Document
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : DocumentRepository {
    override fun getDocument(documentId: Long): Flow<Document> {
        return database.documentDao().getById(documentId)
            .map { it.map() }
            .distinctUntilChanged()
    }
}