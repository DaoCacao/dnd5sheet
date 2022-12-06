package dao.cacao.dnd5sheet.domain.use_case

import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import javax.inject.Inject

class CreateNewSheetUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
    private val abilityRepository: AbilityRepository,
) : suspend () -> Long {
    override suspend fun invoke(): Long {
        val sheetId = sheetRepository.createSheet()
        abilityRepository.createAbilities(sheetId)
        return sheetId
    }
}