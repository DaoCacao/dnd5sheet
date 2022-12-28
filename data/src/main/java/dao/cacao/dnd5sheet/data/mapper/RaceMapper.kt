package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookRaceEntity
import dao.cacao.dnd5sheet.domain.model.Race

fun PlayersHandbookRaceEntity.map() = Race(
    id = raceId,
    name = name,
)