package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SubraceRepository
import dao.cacao.dnd5sheet.domain.model.Subrace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubraceRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : SubraceRepository {
    override fun getSubraces(): Flow<List<Subrace>> {
        return database.playersHandbookSubraceDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override fun getSubraces(raceId: String): Flow<List<Subrace>> {
        return database.playersHandbookSubraceDao().getAllByRaceId(raceId)
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override fun getSubrace(subraceId: String): Flow<Subrace> {
        return database.playersHandbookSubraceDao().getBySubraceId(subraceId)
            .map { it.map() }
            .distinctUntilChanged()
    }
}