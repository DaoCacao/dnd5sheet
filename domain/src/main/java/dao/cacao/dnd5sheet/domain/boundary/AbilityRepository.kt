package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Ability
import kotlinx.coroutines.flow.Flow

interface AbilityRepository {
    fun getAbilities(): Flow<List<Ability>>

    suspend fun updateAbilityScore(abilityId: Long, score: Int)
    suspend fun deleteAbilities(sheetId: Long)
}