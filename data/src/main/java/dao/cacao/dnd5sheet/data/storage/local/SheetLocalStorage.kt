package dao.cacao.dnd5sheet.data.storage.local

import dao.cacao.dnd5sheet.data.mapper.SheetMapper
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.model.Sheet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SheetLocalStorage @Inject constructor(
    private val database: AppDatabase,
    private val sheetMapper: SheetMapper,
) {
    suspend fun createSheet(): Flow<Sheet> {
        val entity = sheetMapper.createDraft()
        val id = database.sheetDao().insert(entity)
        return getSheet(id)
    }

    fun getSheet(sheetId: Long): Flow<Sheet> {
        return database.sheetDao().getById(sheetId)
            .flatMapLatest { sheet ->
                combine(
                    database.raceDao().getById(sheet.raceId),
                    database.classDao().getById(sheet.classId),
                ) { race, clazz ->
                    sheetMapper.map(sheet, race, clazz)
                }
            }
            .distinctUntilChanged()
    }

    fun getSheets(): Flow<List<Sheet>> {
        return database.sheetDao().getAll()
            .map { it.map { sheetMapper.map(it) } }
            .distinctUntilChanged()
    }

    suspend fun deleteSheet(sheetId: Long) {
        database.sheetDao().deleteById(sheetId)
    }
}