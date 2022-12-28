package dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.dao.BaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubraceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersHandbookSubraceDao : BaseDao<PlayersHandbookSubraceEntity> {
    @Query("SELECT * FROM players_handbook_subrace")
    fun getAll(): Flow<List<PlayersHandbookSubraceEntity>>

    @Query("SELECT * FROM players_handbook_subrace WHERE race_id = :raceId")
    fun getAllByRaceId(raceId: String): Flow<List<PlayersHandbookSubraceEntity>>

    @Query("SELECT * FROM players_handbook_subrace WHERE subrace_id = :subraceId")
    fun getBySubraceId(subraceId: String): Flow<PlayersHandbookSubraceEntity>
}