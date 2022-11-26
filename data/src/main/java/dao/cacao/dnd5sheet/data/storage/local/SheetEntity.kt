package dao.cacao.dnd5sheet.data.storage.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sheet")
data class SheetEntity(
    @ColumnInfo(name = "sheet_id") @PrimaryKey val sheetId: Long = 0,
    @ColumnInfo(name = "character_name") val characterName: String? = null,
)