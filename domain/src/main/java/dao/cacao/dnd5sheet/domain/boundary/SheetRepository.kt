package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow

interface SheetRepository {
    fun getSheet(sheetId: Long): Flow<Sheet>
    fun getSheets(): Flow<List<Sheet>>

    suspend fun createSheet(): Flow<Sheet>
    suspend fun updateLevel(sheetId: Long, level: String): Flow<Sheet>
    suspend fun updateCharacterName(sheetId: Long, characterName: String): Flow<Sheet>
    suspend fun updateCharacterClass(sheetId: Long, characterClass: String): Flow<Sheet>
    suspend fun updateCharacterRace(sheetId: Long, characterRace: String): Flow<Sheet>
    suspend fun deleteSheet(sheetId: Long)
}