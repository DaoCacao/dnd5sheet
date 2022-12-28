package dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_handbook_ability")
data class PlayersHandbookAbilityEntity(
    @ColumnInfo(name = "ability_id") @PrimaryKey val abilityId: String,
    @ColumnInfo(name = "name") val name: String,
)