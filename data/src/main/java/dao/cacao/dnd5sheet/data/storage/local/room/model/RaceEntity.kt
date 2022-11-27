package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "race")
data class RaceEntity(
    @ColumnInfo(name = "race_id") @PrimaryKey(autoGenerate = true) val raceId: Long = 0,
    @ColumnInfo(name = "document_id") val documentId: Long = 0,
    @ColumnInfo(name = "name") val name: String? = null,
)