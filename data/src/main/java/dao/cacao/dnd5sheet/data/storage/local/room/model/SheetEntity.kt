package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sheet")
data class SheetEntity(
    @ColumnInfo(name = "sheet_id") @PrimaryKey(autoGenerate = true) val sheetId: Long = 0,
    @ColumnInfo(name = "level") val level: Int? = null,
    @ColumnInfo(name = "character_name") val characterName: String? = null,
    @ColumnInfo(name = "character_race") val characterRace: String? = null,
    @ColumnInfo(name = "character_class") val characterClass: String? = null,
    @ColumnInfo(name = "strength") val strength: Int? = null,
    @ColumnInfo(name = "dexterity") val dexterity: Int? = null,
    @ColumnInfo(name = "constitution") val constitution: Int? = null,
    @ColumnInfo(name = "intelligence") val intelligence: Int? = null,
    @ColumnInfo(name = "wisdom") val wisdom: Int? = null,
    @ColumnInfo(name = "charisma") val charisma: Int? = null,
)