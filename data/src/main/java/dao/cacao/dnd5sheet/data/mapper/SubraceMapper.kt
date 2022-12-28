package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubraceEntity
import dao.cacao.dnd5sheet.domain.model.Subrace

fun PlayersHandbookSubraceEntity.map() = Subrace(
    id = subraceId,
    raceId = raceId,
    name = name,
)