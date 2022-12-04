package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.SheetMapper
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SheetRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val sheetMapper: SheetMapper,
) : SheetRepository {

    override fun getSheet(sheetId: Long): Flow<Sheet> {
        return database.sheetDao().getById(sheetId)
            .map { sheetMapper.map(it) }
            .distinctUntilChanged()
    }

    override fun getSheets(): Flow<List<Sheet>> {
        return database.sheetDao().getAll()
            .map { it.map { sheetMapper.map(it) } }
            .distinctUntilChanged()
    }

    override suspend fun createSheet(): Flow<Sheet> {
        val entity = sheetMapper.createDraft()
        val id = database.sheetDao().insert(entity)
        return getSheet(id)
    }

    override suspend fun updateLevel(sheetId: Long, level: Int): Flow<Sheet> {
        database.sheetDao().updateLevel(sheetId, level)
        return getSheet(sheetId)
    }

    override suspend fun updateCharacterName(sheetId: Long, characterName: String): Flow<Sheet> {
        database.sheetDao().updateCharacterName(sheetId, characterName)
        return getSheet(sheetId)
    }

    override suspend fun updateCharacterClass(sheetId: Long, characterClass: String): Flow<Sheet> {
        database.sheetDao().updateCharacterClass(sheetId, characterClass)
        return getSheet(sheetId)
    }

    override suspend fun updateCharacterRace(sheetId: Long, characterRace: String): Flow<Sheet> {
        database.sheetDao().updateCharacterRace(sheetId, characterRace)
        return getSheet(sheetId)
    }

    override suspend fun updateStrength(sheetId: Long, value: Int) {
        database.sheetDao().updateStrength(sheetId, value)
    }

    override suspend fun updateDexterity(sheetId: Long, value: Int) {
        database.sheetDao().updateDexterity(sheetId, value)
    }

    override suspend fun updateConstitution(sheetId: Long, value: Int) {
        database.sheetDao().updateConstitution(sheetId, value)
    }

    override suspend fun updateIntelligence(sheetId: Long, value: Int) {
        database.sheetDao().updateIntelligence(sheetId, value)
    }

    override suspend fun updateWisdom(sheetId: Long, value: Int) {
        database.sheetDao().updateWisdom(sheetId, value)
    }

    override suspend fun updateCharisma(sheetId: Long, value: Int) {
        database.sheetDao().updateCharisma(sheetId, value)
    }

    override suspend fun deleteSheet(sheetId: Long) {
        database.sheetDao().deleteById(sheetId)
    }
}