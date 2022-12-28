package dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_handbook_class")
data class PlayersHandbookClassEntity(
    @ColumnInfo(name = "class_id") @PrimaryKey val classId: String,
    @ColumnInfo(name = "name") val name: String,
)