package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.domain.model.Document

fun DocumentEntity.map() = Document(
    id = documentId,
    name = name ?: "",
    text = text ?: "",
)
