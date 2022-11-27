package dao.cacao.dnd5sheet.domain.use_case.race

import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import dao.cacao.dnd5sheet.domain.model.Race
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRacesUseCase @Inject constructor(
    private val raceRepository: RaceRepository,
) : () -> Flow<List<Race>> {
    override fun invoke() = raceRepository.getRaces()
}

