package dao.cacao.dnd5sheet.domain.use_case.sheet

import javax.inject.Inject

class UpdateAbilityScoreUseCase @Inject constructor() {
    suspend operator fun invoke(sheetId: Long, abilityId: String, score: Int) {
        TODO("Not Implemented Yet")
    }
}