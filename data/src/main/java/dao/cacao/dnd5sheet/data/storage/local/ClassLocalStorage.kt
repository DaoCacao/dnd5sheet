package dao.cacao.dnd5sheet.data.storage.local

import dao.cacao.dnd5sheet.data.mapper.ClassMapper
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClassLocalStorage @Inject constructor(
    private val database: AppDatabase,
    private val classMapper: ClassMapper,
) {
    fun getClasses(): Flow<List<CharacterClass>> {
        return database.classDao().getAll()
            .map { it.map(classMapper::map) }
    }
}