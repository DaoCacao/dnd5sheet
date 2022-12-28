package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.model.Ability
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AbilityRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : AbilityRepository {

    override fun getAbilities(): Flow<List<Ability>> {
        return database.playersHandbookAbilityDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override suspend fun updateAbilityScore(abilityId: Long, score: Int) {
        database.abilityDao().updateScore(abilityId, score)
    }

    override suspend fun deleteAbilities(sheetId: Long) {
        database.abilityDao().deleteBySheetId(sheetId)
    }
}