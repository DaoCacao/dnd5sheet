package dao.cacao.dnd5sheet.domain.boundary

interface AbilityRepository {
    suspend fun updateAbilityScore(abilityId: Long, score: Int)
    suspend fun deleteAbilities(sheetId: Long)
}