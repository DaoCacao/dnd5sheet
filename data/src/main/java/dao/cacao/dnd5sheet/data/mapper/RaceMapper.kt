package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.domain.model.Race

fun RaceEntity.map() = Race(
    id = raceId,
    documentId = documentId,
    name = name ?: "",
)