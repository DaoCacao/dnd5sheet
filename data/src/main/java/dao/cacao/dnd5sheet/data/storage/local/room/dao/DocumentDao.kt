package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao : BaseDao<DocumentEntity> {

    @Query("SELECT * FROM document")
    fun getAll(): Flow<List<DocumentEntity>>

    @Query("SELECT * FROM document WHERE document_id == :documentId")
    fun getById(documentId: Long): Flow<DocumentEntity>
}

