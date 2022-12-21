package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Race
import kotlinx.coroutines.flow.Flow

interface RaceRepository {
    fun getRaces(): Flow<List<Race>>
    suspend fun updateCharacterRace(sheetId: Long, raceId: Long)
}