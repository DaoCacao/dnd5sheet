package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.Embedded
import androidx.room.Relation
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookRaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubRaceEntity

data class SheetFull(
    @Embedded
    val sheet: SheetEntity,
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
        parentColumn = "race_id",
        entityColumn = "race_id",
    )
    val characterRace: PlayersHandbookRaceEntity?,
    @Relation(
        parentColumn = "sub_race_id",
        entityColumn = "sub_race_id",
    )
    val characterSubRace: PlayersHandbookSubRaceEntity?,
    @Relation(
        parentColumn = "class_id",
        entityColumn = "class_id",
    )
    val characterClass: PlayersHandbookClassEntity?,
)