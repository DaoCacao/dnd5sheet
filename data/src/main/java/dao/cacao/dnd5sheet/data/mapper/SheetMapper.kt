package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetFull
import dao.cacao.dnd5sheet.domain.model.Sheet

fun createDraftSheet() = SheetEntity()

fun SheetFull.map() = Sheet(
    id = sheet.sheetId,
    character = character.map(),
    characterRace = characterRace?.map(),
    characterClass = characterClass?.map(),
    abilities = abilities.map { it.map() },
    skills = skills.map { it.map() },
)