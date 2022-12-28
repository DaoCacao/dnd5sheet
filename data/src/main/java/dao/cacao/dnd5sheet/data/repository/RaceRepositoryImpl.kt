package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import dao.cacao.dnd5sheet.domain.model.Race
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RaceRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : RaceRepository {
    override fun getRaces(): Flow<List<Race>> {
        return database.playersHandbookRaceDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override fun getRace(raceId: String): Flow<Race> {
        return database.playersHandbookRaceDao().getByRaceId(raceId)
            .map { it.map() }
            .distinctUntilChanged()
    }
}