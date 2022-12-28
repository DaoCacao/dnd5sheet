package dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "players_handbook_ability_increase", primaryKeys = ["ability_id", "race_id", "sub_race_id"])
data class PlayersHandbookAbilityIncreaseEntity(
    @ColumnInfo(name = "ability_id") val abilityId: String,
    @ColumnInfo(name = "race_id") val raceId: String,
    @ColumnInfo(name = "sub_race_id") val subRaceId: String,
    @ColumnInfo(name = "value") val value: Int,
)