package dao.cacao.dnd5sheet.domain.use_case.sheet

import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import javax.inject.Inject

class UpdateProficiencyBonusUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
) {
    suspend operator fun invoke(sheetId: Long, proficiencyBonus: Int) {
        sheetRepository.updateProficiencyBonus(
            sheetId = sheetId,
            proficiencyBonus = proficiencyBonus,
        )
    }
}