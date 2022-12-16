package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterSheet(
    @Embedded
    val sheet: SheetEntity,
    @Relation(
        parentColumn = "sheet_id",
        entityColumn = "sheet_id",
    )
    val character: CharacterEntity,
    @Relation(
        parentColumn = "sheet_id",
        entityColumn = "sheet_id",
    )
    val abilities: List<AbilityEntity>,
    @Relation(
        parentColumn = "sheet_id",
        entityColumn = "sheet_id",
    )
    val skills: List<SkillEntity>,
)