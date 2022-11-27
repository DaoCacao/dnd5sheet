package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.domain.model.Sheet
import javax.inject.Inject

class SheetMapper @Inject constructor() {
    fun createDraft() = SheetEntity(
        isDraft = true,
    )

    fun map(entity: SheetEntity) = Sheet(
        id = entity.sheetId,
        characterName = entity.characterName ?: "",
        level = entity.level ?: 0,
        clazz = entity.clazz ?: "",
        race = entity.race ?: "",
    )

    fun map(model: Sheet) = SheetEntity(
        sheetId = model.id,
        characterName = model.characterName,
    )
}