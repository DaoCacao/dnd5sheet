package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookAbility

interface AbilityRepository {
    suspend fun updateAbilityScore(abilityId: Long, score: Int)
    suspend fun deleteAbilities(sheetId: Long)
}