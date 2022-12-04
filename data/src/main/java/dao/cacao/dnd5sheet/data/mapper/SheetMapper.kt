package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.domain.model.Sheet
import javax.inject.Inject

class SheetMapper @Inject constructor() {
    fun createDraft() = SheetEntity()

    fun map(sheet: SheetEntity) = Sheet(
        id = sheet.sheetId,
        level = sheet.level,
        characterName = sheet.characterName,
        characterRace = sheet.characterRace,
        characterClass = sheet.characterClass,
        strength = sheet.strength,
        dexterity = sheet.dexterity,
        constitution = sheet.constitution,
        intelligence = sheet.intelligence,
        wisdom = sheet.wisdom,
        charisma = sheet.charisma,
    )
}