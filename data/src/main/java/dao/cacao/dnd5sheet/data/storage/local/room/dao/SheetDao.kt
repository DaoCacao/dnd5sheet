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

    @Query("UPDATE sheet SET level = :level WHERE sheet_id == :sheetId")
    suspend fun updateLevel(sheetId: Long, level: Int)

    @Query("UPDATE sheet SET character_name = :characterName WHERE sheet_id == :sheetId")
    suspend fun updateCharacterName(sheetId: Long, characterName: String)

    @Query("UPDATE sheet SET character_race = :characterRace WHERE sheet_id == :sheetId")
    suspend fun updateCharacterRace(sheetId: Long, characterRace: String)

    @Query("UPDATE sheet SET character_class = :characterClass WHERE sheet_id == :sheetId")
    suspend fun updateCharacterClass(sheetId: Long, characterClass: String)

    @Query("UPDATE sheet SET strength = :value WHERE sheet_id == :sheetId")
    suspend fun updateStrength(sheetId: Long, value: Int)

    @Query("UPDATE sheet SET dexterity = :value WHERE sheet_id == :sheetId")
    suspend fun updateDexterity(sheetId: Long, value: Int)

    @Query("UPDATE sheet SET constitution = :value WHERE sheet_id == :sheetId")
    suspend fun updateConstitution(sheetId: Long, value: Int)

    @Query("UPDATE sheet SET intelligence = :value WHERE sheet_id == :sheetId")
    suspend fun updateIntelligence(sheetId: Long, value: Int)

    @Query("UPDATE sheet SET wisdom = :value WHERE sheet_id == :sheetId")
    suspend fun updateWisdom(sheetId: Long, value: Int)

    @Query("UPDATE sheet SET charisma = :value WHERE sheet_id == :sheetId")
    suspend fun updateCharisma(sheetId: Long, value: Int)

    @Query("DELETE FROM sheet WHERE sheet_id == :sheetId")
    suspend fun deleteById(sheetId: Long)
}

