package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SheetDao : BaseDao<SheetEntity> {

    @Query("SELECT * FROM sheet")
    fun getAll(): Flow<List<SheetEntity>>

    @Query("SELECT * FROM sheet WHERE sheet_id == :sheetId")
    fun getById(sheetId: Long): Flow<SheetEntity>

    @Query("DELETE FROM sheet WHERE sheet_id == :sheetId")
    suspend fun deleteById(sheetId: Long)
}

