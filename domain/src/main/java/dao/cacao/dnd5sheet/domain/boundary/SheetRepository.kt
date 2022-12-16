package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow

interface SheetRepository {
    suspend fun createSheet(): Sheet
    fun getSheet(characterId: Long): Flow<Sheet>
    fun getSheets(): Flow<List<Sheet>>
    suspend fun deleteSheet(sheetId: Long)
}