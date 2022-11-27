package dao.cacao.dnd5sheet.presentation.preview

import dao.cacao.dnd5sheet.domain.model.Sheet

fun previewSheet(
    id: Int = 1,
    characterName: String = "Character #$id",
    level: Int = id,
    clazz: String = "Class",
    race: String = "Race",
) = Sheet(
    id = id.toLong(),
    characterName = characterName,
    level = level,
    clazz = clazz,
    race = race,
)


fun previewSheets(count: Int) = List(count) { previewSheet(id = it + 1) }