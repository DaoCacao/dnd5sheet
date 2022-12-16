package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import javax.inject.Inject

class SkillRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : SkillRepository {

    override suspend fun updateSkillProficiency(skillId: Long, proficiency: Boolean) {
        database.skillDao().updateProficiency(skillId, proficiency)
    }

    override suspend fun deleteSkills(sheetId: Long) {
        database.skillDao().deleteBySheetId(sheetId)
    }
}