package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow

interface SheetRepository {
    suspend fun createSheet(): Sheet

    fun getSheets(): Flow<List<Sheet>>
    fun getSheet(sheetId: Long): Flow<Sheet>

    suspend fun updateCharacterRace(sheetId: Long, raceId: String)
    suspend fun updateCharacterSubRace(sheetId: Long, subRaceId: String)
    suspend fun updateCharacterClass(sheetId: Long, classId: String)

    suspend fun updateLevel(characterId: Long, level: Int)
    suspend fun updateCharacterName(characterId: Long, characterName: String)
    suspend fun updateProficiencyBonus(characterId: Long, proficiencyBonus: Int)

    suspend fun deleteSheet(sheetId: Long)
}