package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetFull
import dao.cacao.dnd5sheet.domain.model.Sheet

fun createDraftSheet() = SheetEntity()

fun SheetFull.map() = Sheet(
    id = sheet.sheetId,
    characterRace = characterRace?.map(),
    characterSubRace = characterSubRace?.map(),
    characterClass = characterClass?.map(),
    level = sheet.level,
    characterName = sheet.characterName,
    proficiencyBonus = sheet.proficiencyBonus,
    abilities = emptyList(), // abilities.map { it.map() },
    skills = emptyList(), // skills.map { it.map() },
)