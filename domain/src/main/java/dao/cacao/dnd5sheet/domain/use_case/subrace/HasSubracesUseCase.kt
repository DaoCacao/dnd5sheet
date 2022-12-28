package dao.cacao.dnd5sheet.domain.use_case.subrace

import dao.cacao.dnd5sheet.domain.boundary.SubraceRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HasSubracesUseCase @Inject constructor(
    private val subraceRepository: SubraceRepository,
) {
    suspend operator fun invoke(raceId: String): Boolean {
        return subraceRepository.getSubraces(raceId = raceId)
            .first()
            .isNotEmpty()
    }
}