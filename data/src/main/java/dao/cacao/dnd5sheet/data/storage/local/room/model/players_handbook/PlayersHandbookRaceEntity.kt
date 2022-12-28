package dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_handbook_race")
data class PlayersHandbookRaceEntity(
    @ColumnInfo(name = "race_id") @PrimaryKey val raceId: String,
    @ColumnInfo(name = "name") val name: String,
)

