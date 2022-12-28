package dao.cacao.dnd5sheet.domain.use_case.subrace

import dao.cacao.dnd5sheet.domain.boundary.SubraceRepository
import dao.cacao.dnd5sheet.domain.model.Subrace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersHandbookSubracesUseCase @Inject constructor(
    private val subraceRepository: SubraceRepository,
) {
    operator fun invoke(raceId: String): Flow<List<Subrace>> {
        return subraceRepository.getSubraces(
            raceId = raceId,
        )
    }
}