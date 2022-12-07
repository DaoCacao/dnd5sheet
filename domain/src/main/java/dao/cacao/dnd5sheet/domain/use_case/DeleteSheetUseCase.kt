package dao.cacao.dnd5sheet.domain.use_case

import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import javax.inject.Inject

class DeleteSheetUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
    private val abilityRepository: AbilityRepository,
    private val skillRepository: SkillRepository,
) : suspend (Long) -> Unit {
    override suspend fun invoke(sheetId: Long) {
        sheetRepository.deleteSheet(sheetId)
        abilityRepository.deleteAbilities(sheetId)
        skillRepository.deleteSkills(sheetId)
    }
}