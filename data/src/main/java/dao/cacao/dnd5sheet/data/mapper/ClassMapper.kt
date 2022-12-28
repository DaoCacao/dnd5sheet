package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookClassEntity
import dao.cacao.dnd5sheet.domain.model.CharacterClass

fun PlayersHandbookClassEntity.map() = CharacterClass(
    id = classId,
    name = name,
)