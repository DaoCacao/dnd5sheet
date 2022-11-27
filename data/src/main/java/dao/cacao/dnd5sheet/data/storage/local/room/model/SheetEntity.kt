package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sheet")
data class SheetEntity(
    @ColumnInfo(name = "sheet_id") @PrimaryKey(autoGenerate = true) val sheetId: Long = 0,
    @ColumnInfo(name = "character_name") val characterName: String? = null,
    @ColumnInfo(name = "level") val level: Int? = null,
    @ColumnInfo(name = "class") val clazz: String? = null,
    @ColumnInfo(name = "race") val race: String? = null,
    @ColumnInfo(name = "is_draft") val isDraft: Boolean? = null,
)