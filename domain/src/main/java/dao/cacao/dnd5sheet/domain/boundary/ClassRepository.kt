package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.CharacterClass
import kotlinx.coroutines.flow.Flow

interface ClassRepository {
    fun getClasses(): Flow<List<CharacterClass>>
    suspend fun updateCharacterClass(sheetId: Long, classId: Long)
}