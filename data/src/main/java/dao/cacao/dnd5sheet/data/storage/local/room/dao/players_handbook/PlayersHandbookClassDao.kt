package dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.dao.BaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookClassEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersHandbookClassDao : BaseDao<PlayersHandbookClassEntity> {
    @Query("SELECT * FROM players_handbook_class")
    fun getAll(): Flow<List<PlayersHandbookClassEntity>>

    @Query("SELECT * FROM players_handbook_class WHERE class_id = :classId")
    fun getByClassId(classId: String): Flow<PlayersHandbookClassEntity>
}