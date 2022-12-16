package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dao.cacao.dnd5sheet.data.storage.local.room.model.CharacterSheet
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SheetDao : BaseDao<SheetEntity> {

    @Transaction
    @Query("SELECT * FROM sheet")
    fun getAll(): Flow<List<CharacterSheet>>

    @Transaction
    @Query("SELECT * FROM sheet WHERE sheet_id == :sheetId")
    fun getById(sheetId: Long): Flow<CharacterSheet>

    @Query("DELETE FROM sheet WHERE sheet_id == :sheetId")
    fun deleteById(sheetId: Long)
}

