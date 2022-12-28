package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.SubRace
import kotlinx.coroutines.flow.Flow

interface SubRaceRepository {
    fun getSubRaces(): Flow<List<SubRace>>
    fun getSubRaces(raceId: String): Flow<List<SubRace>>
    fun getSubRace(subRaceId: String): Flow<SubRace>
}