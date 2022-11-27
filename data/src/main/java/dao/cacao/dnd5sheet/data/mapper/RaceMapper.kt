package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.domain.model.race.Race
import javax.inject.Inject

class RaceMapper @Inject constructor() {
    fun map(entity: RaceEntity) = Race(
        id = entity.raceId,
        documentId = entity.documentId,
        name = entity.name ?: "",
    )
}