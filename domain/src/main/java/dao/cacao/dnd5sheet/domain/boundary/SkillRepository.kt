package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookSkill

interface SkillRepository {
    suspend fun updateSkillProficiency(skillId: Long, proficiency: Boolean)
    suspend fun deleteSkills(sheetId: Long)
}