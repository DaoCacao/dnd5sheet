package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.FullSheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.domain.model.Sheet

fun createDraft() = SheetEntity()

fun SheetEntity.map() = Sheet(
    id = this.sheetId,
    level = this.level,
    characterName = this.characterName,
    characterRace = this.characterRace,
    characterClass = this.characterClass,
    proficiencyBonus = this.proficiencyBonus,
    abilities = emptyList(),
    skills = emptyList(),
)

fun FullSheetEntity.map() = Sheet(
    id = sheet.sheetId,
    level = sheet.level,
    characterName = sheet.characterName,
    characterRace = sheet.characterRace,
    characterClass = sheet.characterClass,
    proficiencyBonus = sheet.proficiencyBonus,
    abilities = abilities.map { it.map() },
    skills = skills.map { it.map() },
)