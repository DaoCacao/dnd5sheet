package dao.cacao.dnd5sheet.domain.use_case.sheet

import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import javax.inject.Inject

class DeleteSheetUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
) : suspend (Long) -> Unit {
    override suspend fun invoke(sheetId: Long) = sheetRepository.deleteSheet(sheetId)
}