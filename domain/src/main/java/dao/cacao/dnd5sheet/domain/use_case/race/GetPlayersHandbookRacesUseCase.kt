package dao.cacao.dnd5sheet.domain.use_case.race

import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import dao.cacao.dnd5sheet.domain.model.Race
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersHandbookRacesUseCase @Inject constructor(
    private val raceRepository: RaceRepository,
) {
    operator fun invoke(): Flow<List<Race>> {
        return raceRepository.getRaces()
    }
}