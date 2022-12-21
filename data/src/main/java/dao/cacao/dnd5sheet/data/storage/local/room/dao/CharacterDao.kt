package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao : BaseDao<CharacterEntity> {

    @Query("SELECT * FROM character")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE character_id == :characterId")
    fun getById(characterId: Long): Flow<CharacterEntity>

    @Query("UPDATE character SET level = :level WHERE character_id == :characterId")
    suspend fun updateLevel(characterId: Long, level: Int)

    @Query("UPDATE character SET character_name = :characterName WHERE character_id == :characterId")
    suspend fun updateCharacterName(characterId: Long, characterName: String)

    @Query("UPDATE character SET proficiency_bonus = :proficiencyBonus WHERE character_id == :characterId")
    suspend fun updateProficiencyBonus(characterId: Long, proficiencyBonus: Int)

    @Query("DELETE FROM character WHERE character_id == :characterId")
    suspend fun deleteById(characterId: Long)

    @Query("DELETE FROM character WHERE sheet_id == :sheetId")
    suspend fun deleteBySheetId(sheetId: Long)
}

