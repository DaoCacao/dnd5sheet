package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.domain.model.CharacterClass

fun ClassEntity.map() = CharacterClass(
    id = classId,
    documentId = documentId,
    name = name ?: "",
)