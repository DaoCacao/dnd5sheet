package dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.dao.BaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersHandbookAbilityDao : BaseDao<PlayersHandbookAbilityEntity> {
    @Query("SELECT * FROM players_handbook_ability")
    fun getAll(): Flow<List<PlayersHandbookAbilityEntity>>
}