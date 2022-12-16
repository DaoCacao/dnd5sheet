package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sheet")
data class SheetEntity(
    @ColumnInfo(name = "sheet_id") @PrimaryKey(autoGenerate = true) val sheetId: Long = 0,
)