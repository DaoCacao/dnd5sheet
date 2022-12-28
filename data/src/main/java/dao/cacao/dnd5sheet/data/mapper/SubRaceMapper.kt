package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubRaceEntity
import dao.cacao.dnd5sheet.domain.model.SubRace

fun PlayersHandbookSubRaceEntity.map() = SubRace(
    id = raceId,
    name = name,
)