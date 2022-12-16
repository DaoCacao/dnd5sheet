package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @ColumnInfo(name = "character_id") @PrimaryKey(autoGenerate = true) val characterId: Long = 0,
    @ColumnInfo(name = "sheet_id") val sheetId: Long = 0,
    @ColumnInfo(name = "level") val level: Int? = null,
    @ColumnInfo(name = "character_name") val characterName: String? = null,
    @ColumnInfo(name = "character_race") val characterRace: String? = null,
    @ColumnInfo(name = "character_class") val characterClass: String? = null,
    @ColumnInfo(name = "proficiency_bonus") val proficiencyBonus: Int? = null,
)