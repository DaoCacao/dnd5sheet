package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.domain.model.Sheet
import javax.inject.Inject

class SheetMapper @Inject constructor(
    private val raceMapper: RaceMapper,
    private val classMapper: ClassMapper,
) {
    fun createDraft() = SheetEntity()

    fun map(
        sheet: SheetEntity,
        race: RaceEntity? = null,
        clazz: ClassEntity? = null,
    ) = Sheet(
        id = sheet.sheetId,
        characterName = sheet.characterName ?: "",
        characterRace = race?.let { raceMapper.map(it) },
        characterClass = clazz?.let { classMapper.map(it) },
    )
}