package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.AbilityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityDao : BaseDao<AbilityEntity> {

    @Query("SELECT * FROM ability WHERE sheet_id = :sheetId")
    fun getAllBySheetId(sheetId: Long): Flow<List<AbilityEntity>>

    @Query("UPDATE ability SET score = :score WHERE ability_id == :abilityId")
    suspend fun updateScore(abilityId: Long, score: Int)

    @Query("DELETE FROM ability WHERE sheet_id == :sheetId")
    suspend fun deleteBySheetId(sheetId: Long)
}

