package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill")
data class SkillEntity(
    @ColumnInfo(name = "skill_id") @PrimaryKey(autoGenerate = true) val skillId: Long = 0,
    @ColumnInfo(name = "sheet_id") val sheetId: Long = 0,
    @ColumnInfo(name = "ability_id") val abilityId: Long = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "proficiency") val proficiency: Boolean? = null,
)