package dao.cacao.dnd5sheet.domain.use_case.clazz

import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersHandbookClassesUseCase @Inject constructor(
    private val classRepository: ClassRepository,
) {
    operator fun invoke(): Flow<List<CharacterClass>> {
        return classRepository.getClasses()
    }
}