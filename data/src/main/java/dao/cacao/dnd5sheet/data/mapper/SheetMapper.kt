package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.domain.model.Sheet
import javax.inject.Inject

class SheetMapper @Inject constructor() {
    fun map(entity: SheetEntity) = Sheet(
        id = entity.sheetId,
        characterName = entity.characterName ?: "",
    )

    fun map(model: Sheet) = SheetEntity(
        sheetId = model.id,
        characterName = model.characterName,
    )
}