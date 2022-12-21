package dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "sheet_to_race", primaryKeys = ["sheet_id"])
data class SheetToRaceCrossRef(
    @ColumnInfo(name = "sheet_id") val sheetId: Long,
    @ColumnInfo(name = "race_id") val raceId: Long,
)