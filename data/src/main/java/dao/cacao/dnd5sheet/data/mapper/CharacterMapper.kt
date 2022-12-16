package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.CharacterEntity
import dao.cacao.dnd5sheet.domain.model.Character

fun createCharacterDraft(sheetId: Long) = CharacterEntity(sheetId = sheetId)

fun CharacterEntity.map() = Character(
    id = this.characterId,
    level = this.level,
    characterName = this.characterName,
    characterRace = this.characterRace,
    characterClass = this.characterClass,
    proficiencyBonus = this.proficiencyBonus,
)