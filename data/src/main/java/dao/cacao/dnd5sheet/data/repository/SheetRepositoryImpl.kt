package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.createDraft
import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SheetRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : SheetRepository {

    override fun getSheet(sheetId: Long): Flow<Sheet> {
        return database.sheetDao().getById(sheetId)
            .map { it.map() }
            .distinctUntilChanged()
    }

    override fun getSheets(): Flow<List<Sheet>> {
        return database.sheetDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override suspend fun createSheet(): Long {
        val entity = createDraft()
        return database.sheetDao().insert(entity)
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

    override suspend fun deleteSheet(sheetId: Long) {
        database.sheetDao().deleteById(sheetId)
    }
}