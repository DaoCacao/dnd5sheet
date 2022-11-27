package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import javax.inject.Inject

class ClassMapper @Inject constructor() {
    fun map(entity: ClassEntity) = CharacterClass(
        id = entity.classId,
        documentId = entity.documentId,
        name = entity.name ?: "",
    )
}