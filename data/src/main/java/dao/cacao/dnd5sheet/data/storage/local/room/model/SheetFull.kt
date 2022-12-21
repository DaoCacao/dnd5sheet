package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref.SheetToClassCrossRef
import dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref.SheetToRaceCrossRef

data class SheetFull(
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
    @Relation(
        parentColumn = "sheet_id",
        entityColumn = "race_id",
        associateBy = Junction(SheetToRaceCrossRef::class),
    )
    val characterRace: RaceEntity?,
    @Relation(
        parentColumn = "sheet_id",
        entityColumn = "class_id",
        associateBy = Junction(SheetToClassCrossRef::class),
    )
    val characterClass: ClassEntity?,
)