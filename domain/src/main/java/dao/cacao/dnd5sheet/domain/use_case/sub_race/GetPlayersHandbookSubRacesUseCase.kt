package dao.cacao.dnd5sheet.domain.use_case.sub_race

import dao.cacao.dnd5sheet.domain.boundary.SubRaceRepository
import dao.cacao.dnd5sheet.domain.model.SubRace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersHandbookSubRacesUseCase @Inject constructor(
    private val subRaceRepository: SubRaceRepository,
) {
    operator fun invoke(raceId: String): Flow<List<SubRace>> {
        return subRaceRepository.getSubRaces(
            raceId = raceId,
        )
    }
}