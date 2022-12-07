package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookSkill
import javax.inject.Inject

class SkillRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : SkillRepository {

    override suspend fun createSkill(skill: PlayersHandbookSkill, sheetId: Long, abilityId: Long): Long {
        val entity = skill.map(sheetId, abilityId)
        return database.skillDao().insert(entity)
    }

    override suspend fun updateSkillProficiency(skillId: Long, proficiency: Boolean) {
        database.skillDao().updateProficiency(skillId, proficiency)
    }

    override suspend fun deleteSkills(sheetId: Long) {
        database.skillDao().deleteBySheetId(sheetId)
    }
}