package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.CharacterSheet
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.domain.model.Sheet

fun createDraftSheet() = SheetEntity()

fun CharacterSheet.map() = Sheet(
    id = sheet.sheetId,
    character = character.map(),
    abilities = abilities.map { it.map() },
    skills = skills.map { it.map() },
)