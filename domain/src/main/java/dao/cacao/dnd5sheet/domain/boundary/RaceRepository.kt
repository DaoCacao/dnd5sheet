package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.race.Race
import kotlinx.coroutines.flow.Flow

interface RaceRepository {
    fun getRaces(): Flow<List<Race>>
}