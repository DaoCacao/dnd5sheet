package dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.dao.BaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityIncreaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersHandbookAbilityIncreaseDao : BaseDao<PlayersHandbookAbilityIncreaseEntity> {
    @Query("SELECT * FROM players_handbook_ability_increase")
    fun getAll(): Flow<List<PlayersHandbookAbilityIncreaseEntity>>
}