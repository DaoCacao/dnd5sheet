package dao.cacao.dnd5sheet.presentation.preview

import dao.cacao.dnd5sheet.domain.model.Sheet

fun previewSheet(
    id: Int = 1,
    characterName: String = "Character #$id",
    level: String = id.toString(),
    clazz: String = "Class",
    race: String = "Race",
) = Sheet(
    id = id.toLong(),
    level = level,
    characterName = characterName,
    characterRace = race,
    characterClass = clazz,
)


fun previewSheets(count: Int) = List(count) { previewSheet(id = it + 1) }