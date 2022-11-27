package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.RaceLocalStorage
import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import javax.inject.Inject

class RaceRepositoryImpl @Inject constructor(
    private val raceLocalStorage: RaceLocalStorage,
) : RaceRepository {
    override fun getRaces() = raceLocalStorage.getRaces()
}