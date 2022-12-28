package dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.dao.BaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookRaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersHandbookRaceDao : BaseDao<PlayersHandbookRaceEntity> {
    @Query("SELECT * FROM players_handbook_race")
    fun getAll(): Flow<List<PlayersHandbookRaceEntity>>

    @Query("SELECT * FROM players_handbook_race WHERE race_id = :raceId")
    fun getByRaceId(raceId: String): Flow<PlayersHandbookRaceEntity>
}