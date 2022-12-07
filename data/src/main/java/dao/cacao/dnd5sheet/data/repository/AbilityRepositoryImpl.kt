package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookAbility
import javax.inject.Inject

class AbilityRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : AbilityRepository {

    override suspend fun createAbility(ability: PlayersHandbookAbility, sheetId: Long): Long {
        val entity = ability.map(sheetId)
        return database.abilityDao().insert(entity)
    }

    override suspend fun updateAbilityScore(abilityId: Long, score: Int) {
        database.abilityDao().updateScore(abilityId, score)
    }

    override suspend fun deleteAbilities(sheetId: Long) {
        database.abilityDao().deleteBySheetId(sheetId)
    }
}