package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class AbilityEntity(
    @ColumnInfo(name = "ability_id") @PrimaryKey(autoGenerate = true) val abilityId: Long = 0,
    @ColumnInfo(name = "sheet_id") val sheetId: Long = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "score") val score: Int? = null,
)