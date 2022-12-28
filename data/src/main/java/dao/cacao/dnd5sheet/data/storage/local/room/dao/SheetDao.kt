package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetFull
import kotlinx.coroutines.flow.Flow

@Dao
interface SheetDao : BaseDao<SheetEntity> {

    @Transaction
    @Query("SELECT * FROM sheet")
    fun getAll(): Flow<List<SheetFull>>

    @Transaction
    @Query("SELECT * FROM sheet WHERE sheet_id == :sheetId")
    fun getBySheetId(sheetId: Long): Flow<SheetFull>

    @Query("UPDATE sheet SET race_id = :raceId WHERE sheet_id = :sheetId")
    suspend fun updateRaceId(sheetId: Long, raceId: String)

    @Query("UPDATE sheet SET sub_race_id = :subRaceId WHERE sheet_id = :sheetId")
    suspend fun updateSubRaceId(sheetId: Long, subRaceId: String)

    @Query("UPDATE sheet SET class_id = :classId WHERE sheet_id = :sheetId")
    suspend fun updateClassId(sheetId: Long, classId: String)

    @Query("UPDATE sheet SET level = :level WHERE sheet_id == :sheetId")
    suspend fun updateLevel(sheetId: Long, level: Int)

    @Query("UPDATE sheet SET character_name = :characterName WHERE sheet_id == :sheetId")
    suspend fun updateCharacterName(sheetId: Long, characterName: String)

    @Query("UPDATE sheet SET proficiency_bonus = :proficiencyBonus WHERE sheet_id == :sheetId")
    suspend fun updateProficiencyBonus(sheetId: Long, proficiencyBonus: Int)

    @Query("DELETE FROM sheet WHERE sheet_id == :sheetId")
    suspend fun deleteBySheetId(sheetId: Long)
}

