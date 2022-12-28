package dao.cacao.dnd5sheet.data.repository

import androidx.room.withTransaction
import dao.cacao.dnd5sheet.data.mapper.createDraftSheet
import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SheetRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : SheetRepository {

    override suspend fun createSheet(): Sheet {
        return database.withTransaction {
            val sheetId = database.sheetDao().insert(createDraftSheet())
            getSheet(sheetId).first()
        }
    }

    override fun getSheets(): Flow<List<Sheet>> {
        return database.sheetDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override fun getSheet(sheetId: Long): Flow<Sheet> {
        return database.sheetDao().getBySheetId(sheetId)
            .map { it.map() }
            .distinctUntilChanged()
    }

    override suspend fun updateCharacterRace(sheetId: Long, raceId: String) {
        database.sheetDao().updateRaceId(sheetId = sheetId, raceId = raceId)
    }

    override suspend fun updateCharacterSubrace(sheetId: Long, subraceId: String) {
        database.sheetDao().updateSubraceId(sheetId = sheetId, subraceId = subraceId)
    }

    override suspend fun updateCharacterClass(sheetId: Long, classId: String) {
        database.sheetDao().updateClassId(sheetId = sheetId, classId = classId)
    }

    override suspend fun updateLevel(sheetId: Long, level: Int) {
        database.sheetDao().updateLevel(sheetId, level)
    }

    override suspend fun updateCharacterName(sheetId: Long, characterName: String) {
        database.sheetDao().updateCharacterName(sheetId, characterName)
    }

    override suspend fun updateProficiencyBonus(sheetId: Long, proficiencyBonus: Int) {
        database.sheetDao().updateProficiencyBonus(sheetId, proficiencyBonus)
    }

    override suspend fun deleteSheet(sheetId: Long) {
        database.withTransaction {
            database.sheetDao().deleteBySheetId(sheetId)

            database.abilityDao().deleteBySheetId(sheetId)
            database.skillDao().deleteBySheetId(sheetId)
        }
    }
}