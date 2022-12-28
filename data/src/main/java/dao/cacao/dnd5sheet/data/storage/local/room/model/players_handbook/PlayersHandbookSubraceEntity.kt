package dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_handbook_subrace")
data class PlayersHandbookSubraceEntity(
    @ColumnInfo(name = "subrace_id") @PrimaryKey val subraceId: String,
    @ColumnInfo(name = "race_id") val raceId: String,
    @ColumnInfo(name = "name") val name: String,
)

