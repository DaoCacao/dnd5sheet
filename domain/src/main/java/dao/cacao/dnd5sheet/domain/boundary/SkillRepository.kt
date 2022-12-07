package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookSkill

interface SkillRepository {
    suspend fun createSkill(skill: PlayersHandbookSkill, sheetId: Long, abilityId: Long): Long
    suspend fun updateSkillProficiency(skillId: Long, proficiency: Boolean)
    suspend fun deleteSkills(sheetId: Long)
}