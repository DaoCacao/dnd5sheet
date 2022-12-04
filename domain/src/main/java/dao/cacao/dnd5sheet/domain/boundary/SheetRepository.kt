package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow

interface SheetRepository {
    fun getSheet(sheetId: Long): Flow<Sheet>
    fun getSheets(): Flow<List<Sheet>>

    suspend fun createSheet(): Flow<Sheet>
    suspend fun updateLevel(sheetId: Long, level: Int): Flow<Sheet>
    suspend fun updateCharacterName(sheetId: Long, characterName: String): Flow<Sheet>
    suspend fun updateCharacterClass(sheetId: Long, characterClass: String): Flow<Sheet>
    suspend fun updateCharacterRace(sheetId: Long, characterRace: String): Flow<Sheet>

    suspend fun updateStrength(sheetId: Long, value: Int)
    suspend fun updateDexterity(sheetId: Long, value: Int)
    suspend fun updateConstitution(sheetId: Long, value: Int)
    suspend fun updateIntelligence(sheetId: Long, value: Int)
    suspend fun updateWisdom(sheetId: Long, value: Int)
    suspend fun updateCharisma(sheetId: Long, value: Int)

    suspend fun deleteSheet(sheetId: Long)
}