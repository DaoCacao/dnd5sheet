package dao.cacao.dnd5sheet.domain.boundary

interface AbilityRepository {
    suspend fun createAbilities(sheetId: Long)
    suspend fun updateAbility(abilityId: Long, value: Int)
    suspend fun deleteAbilities(sheetId: Long)
}