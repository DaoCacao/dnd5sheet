package dao.cacao.dnd5sheet.domain.use_case.sheet

import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateSheetUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
) : suspend () -> Flow<Sheet> {
    override suspend fun invoke() = sheetRepository.createSheet()
}