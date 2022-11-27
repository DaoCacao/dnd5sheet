package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.SheetLocalStorage
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import javax.inject.Inject

class SheetRepositoryImpl @Inject constructor(
    private val sheetLocalStorage: SheetLocalStorage,
) : SheetRepository {

    override fun getSheet(sheetId: Long) = sheetLocalStorage.getSheet(sheetId)

    override fun getSheets() = sheetLocalStorage.getSheets()

    override suspend fun createSheet() = sheetLocalStorage.createSheet()

    override suspend fun updateCharacterName(sheetId: Long, characterName: String) = TODO("Not yet implemented")

    override suspend fun updateLevel(sheetId: Long, level: String) = TODO("Not yet implemented")

    override suspend fun updateClass(sheetId: Long, classId: String) = TODO("Not yet implemented")

    override suspend fun updateRace(sheetId: Long, raceId: String) = TODO("Not yet implemented")

    override suspend fun deleteSheet(sheetId: Long) = sheetLocalStorage.deleteSheet(sheetId)
}