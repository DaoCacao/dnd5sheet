package dao.cacao.dnd5sheet.domain.use_case.sheet

import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import javax.inject.Inject

class UpdateClassUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
) {
    suspend operator fun invoke(sheetId: Long, classId: String) {
        sheetRepository.updateCharacterClass(
            sheetId = sheetId,
            classId = classId,
        )
    }
}