package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dao.cacao.dnd5sheet.data.storage.local.room.model.FullSheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SheetDao : BaseDao<SheetEntity> {

    @Query("SELECT * FROM sheet")
    fun getAll(): Flow<List<SheetEntity>>

    @Transaction
    @Query("SELECT * FROM sheet WHERE sheet_id == :sheetId")
    fun getById(sheetId: Long): Flow<FullSheetEntity>

    @Query("UPDATE sheet SET level = :level WHERE sheet_id == :sheetId")
    suspend fun updateLevel(sheetId: Long, level: Int)

    @Query("UPDATE sheet SET character_name = :characterName WHERE sheet_id == :sheetId")
    suspend fun updateCharacterName(sheetId: Long, characterName: String)

    @Query("UPDATE sheet SET character_race = :characterRace WHERE sheet_id == :sheetId")
    suspend fun updateCharacterRace(sheetId: Long, characterRace: String)

    @Query("UPDATE sheet SET character_class = :characterClass WHERE sheet_id == :sheetId")
    suspend fun updateCharacterClass(sheetId: Long, characterClass: String)

    @Query("DELETE FROM sheet WHERE sheet_id == :sheetId")
    suspend fun deleteById(sheetId: Long)
}

