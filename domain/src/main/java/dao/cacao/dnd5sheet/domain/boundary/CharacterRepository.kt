package dao.cacao.dnd5sheet.domain.boundary

import dao.cacao.dnd5sheet.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacter(characterId: Long): Flow<Character>
    suspend fun updateLevel(characterId: Long, level: Int): Flow<Character>
    suspend fun updateCharacterName(characterId: Long, characterName: String): Flow<Character>
    suspend fun updateProficiencyBonus(characterId: Long, proficiencyBonus: Int): Flow<Character>
    suspend fun deleteCharacter(characterId: Long)
}