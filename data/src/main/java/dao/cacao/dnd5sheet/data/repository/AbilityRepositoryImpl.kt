package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import javax.inject.Inject

class AbilityRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : AbilityRepository {

    override suspend fun updateAbilityScore(abilityId: Long, score: Int) {
        database.abilityDao().updateScore(abilityId, score)
    }

    override suspend fun deleteAbilities(sheetId: Long) {
        database.abilityDao().deleteBySheetId(sheetId)
    }
}