package dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_handbook_sub_race")
data class PlayersHandbookSubRaceEntity(
    @ColumnInfo(name = "sub_race_id") @PrimaryKey val subRaceId: String,
    @ColumnInfo(name = "race_id") val raceId: String,
    @ColumnInfo(name = "name") val name: String,
)

