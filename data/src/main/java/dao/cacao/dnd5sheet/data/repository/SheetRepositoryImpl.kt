package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.SheetLocalStorage
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SheetRepositoryImpl @Inject constructor(
    private val sheetLocalStorage: SheetLocalStorage,
) : SheetRepository {

    override suspend fun createSheet(): Flow<Sheet> {
        return sheetLocalStorage.createSheet()
    }

    override fun getSheet(sheetId: Long): Flow<Sheet> {
        return sheetLocalStorage.getSheet(sheetId)
    }

    override fun getSheets(): Flow<List<Sheet>> {
        return sheetLocalStorage.getSheets()
    }

    override suspend fun deleteSheet(sheetId: Long) {
        sheetLocalStorage.deleteSheet(sheetId)
    }
}