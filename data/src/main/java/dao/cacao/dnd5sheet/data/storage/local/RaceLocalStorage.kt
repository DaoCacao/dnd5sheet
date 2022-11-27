package dao.cacao.dnd5sheet.data.storage.local

import dao.cacao.dnd5sheet.data.mapper.RaceMapper
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.model.Race
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RaceLocalStorage @Inject constructor(
    private val database: AppDatabase,
    private val raceMapper: RaceMapper,
) {
    fun getRaces(): Flow<List<Race>> {
        return database.raceDao().getAll()
            .map { it.map(raceMapper::map) }
    }
}