package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao : BaseDao<SkillEntity> {

    @Query("SELECT * FROM skill WHERE sheet_id = :sheetId")
    fun getAllBySheetId(sheetId: Long): Flow<List<SkillEntity>>

    @Query("UPDATE skill SET proficiency = :proficiency WHERE skill_id == :skillId")
    suspend fun updateProficiency(skillId: Long, proficiency: Boolean)

    @Query("DELETE FROM skill WHERE sheet_id == :sheetId")
    suspend fun deleteBySheetId(sheetId: Long)
}

