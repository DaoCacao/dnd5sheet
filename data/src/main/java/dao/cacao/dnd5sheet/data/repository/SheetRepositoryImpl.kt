package dao.cacao.dnd5sheet.data.repository

import androidx.room.withTransaction
import dao.cacao.dnd5sheet.data.mapper.createCharacterDraft
import dao.cacao.dnd5sheet.data.mapper.createDraftSheet
import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.CharacterRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookAbility
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookSkill
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SheetRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val characterRepository: CharacterRepository,
    private val abilityRepository: AbilityRepository,
    private val skillRepository: SkillRepository,
) : SheetRepository {

    override suspend fun createSheet(): Sheet {
        val abilities = PlayersHandbookAbility.values()
        val skills = PlayersHandbookSkill.values()
        return database.withTransaction {
            val sheetId = database.sheetDao().insert(createDraftSheet())
            val characterId = database.characterDao().insert(createCharacterDraft(sheetId))
            val abilityIds = database.abilityDao().insert(abilities.map { it.map(characterId) })
            val abilityToId = abilities.mapIndexed { index, ability -> ability to abilityIds[index] }.toMap()
            val skillIds = database.skillDao().insert(skills.map { it.map(characterId, abilityToId[it.ability]!!) })
            getSheet(sheetId).first()
        }
    }

    override fun getSheet(characterId: Long): Flow<Sheet> {
        return database.sheetDao().getById(characterId)
            .map { it.map() }
            .distinctUntilChanged()
    }

    override fun getSheets(): Flow<List<Sheet>> {
        return database.sheetDao().getAll()
            .map { it.map { it.map() } }
            .distinctUntilChanged()
    }

    override suspend fun deleteSheet(sheetId: Long) {
        characterRepository.deleteCharacter(sheetId)
        abilityRepository.deleteAbilities(sheetId)
        skillRepository.deleteSkills(sheetId)
    }
}