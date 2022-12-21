package dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "sheet_to_class", primaryKeys = ["sheet_id"])
data class SheetToClassCrossRef(
    @ColumnInfo(name = "sheet_id") val sheetId: Long,
    @ColumnInfo(name = "class_id") val classId: Long,
)