package dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.dao.BaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubRaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersHandbookSubRaceDao : BaseDao<PlayersHandbookSubRaceEntity> {
    @Query("SELECT * FROM players_handbook_sub_race")
    fun getAll(): Flow<List<PlayersHandbookSubRaceEntity>>

    @Query("SELECT * FROM players_handbook_sub_race WHERE race_id = :raceId")
    fun getAllByRaceId(raceId: String): Flow<List<PlayersHandbookSubRaceEntity>>

    @Query("SELECT * FROM players_handbook_sub_race WHERE sub_race_id = :subRaceId")
    fun getBySubRaceId(subRaceId: String): Flow<PlayersHandbookSubRaceEntity>
}