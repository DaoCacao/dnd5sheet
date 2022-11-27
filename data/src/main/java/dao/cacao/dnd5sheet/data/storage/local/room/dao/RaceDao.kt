package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceDao : BaseDao<RaceEntity> {

    @Query("SELECT * FROM race")
    fun getAll(): Flow<List<RaceEntity>>

    @Query("SELECT * FROM race WHERE race_id == :raceId")
    fun getById(raceId: Long): Flow<RaceEntity>
}

