package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SubRaceRepository
import dao.cacao.dnd5sheet.domain.model.SubRace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubRaceRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : SubRaceRepository {
    override fun getSubRaces(): Flow<List<SubRace>> {
        return database.playersHandbookSubRaceDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override fun getSubRaces(raceId: String): Flow<List<SubRace>> {
        return database.playersHandbookSubRaceDao().getAllByRaceId(raceId)
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override fun getSubRace(subRaceId: String): Flow<SubRace> {
        return database.playersHandbookSubRaceDao().getBySubRaceId(subRaceId)
            .map { it.map() }
            .distinctUntilChanged()
    }
}