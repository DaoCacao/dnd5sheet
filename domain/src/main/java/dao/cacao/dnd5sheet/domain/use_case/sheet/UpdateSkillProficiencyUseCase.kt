package dao.cacao.dnd5sheet.domain.use_case.sheet

import javax.inject.Inject

class UpdateSkillProficiencyUseCase @Inject constructor() {
    suspend operator fun invoke(sheetId: Long, skillId: String, score: Int) {
        TODO("Not Implemented Yet")
    }
}