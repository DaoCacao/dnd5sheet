package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookAbility
import javax.inject.Inject

class AbilityRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : AbilityRepository {

    override suspend fun createAbilities(sheetId: Long) {
        val abilities = PlayersHandbookAbility.values().map { it.map(sheetId) }
        database.abilityDao().insert(abilities)
    }

    override suspend fun updateAbility(abilityId: Long, value: Int) {
        database.abilityDao().updateValue(abilityId, value)
    }

    override suspend fun deleteAbilities(sheetId: Long) {
        database.abilityDao().deleteBySheetId(sheetId)
    }
}