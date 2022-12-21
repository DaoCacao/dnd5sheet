package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.mapper.map
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.domain.boundary.CharacterRepository
import dao.cacao.dnd5sheet.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : CharacterRepository {

    override suspend fun getCharacter(characterId: Long): Flow<Character> {
        return database.characterDao().getById(characterId)
            .map { it.map() }
            .distinctUntilChanged()
    }

    override suspend fun updateLevel(characterId: Long, level: Int): Flow<Character> {
        database.characterDao().updateLevel(characterId, level)
        return getCharacter(characterId)
    }

    override suspend fun updateCharacterName(characterId: Long, characterName: String): Flow<Character> {
        database.characterDao().updateCharacterName(characterId, characterName)
        return getCharacter(characterId)
    }

    override suspend fun updateProficiencyBonus(characterId: Long, proficiencyBonus: Int): Flow<Character> {
        database.characterDao().updateProficiencyBonus(characterId, proficiencyBonus)
        return getCharacter(characterId)
    }

    override suspend fun deleteCharacter(characterId: Long) {
        database.characterDao().deleteById(characterId)
    }
}