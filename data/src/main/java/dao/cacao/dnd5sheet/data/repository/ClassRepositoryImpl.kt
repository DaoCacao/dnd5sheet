package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref.SheetToClassCrossRef
import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClassRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : ClassRepository {
    override fun getClasses(): Flow<List<CharacterClass>> {
        return database.classDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override suspend fun updateCharacterClass(sheetId: Long, classId: Long) {
        val crossRef = SheetToClassCrossRef(sheetId = sheetId, classId = classId)
        database.sheetToClassDao().insertReplace(crossRef)
    }
}