package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Subrace
import kotlinx.coroutines.flow.Flow

interface SubraceRepository {
    fun getSubraces(): Flow<List<Subrace>>
    fun getSubraces(raceId: String): Flow<List<Subrace>>
    fun getSubrace(subraceId: String): Flow<Subrace>
}