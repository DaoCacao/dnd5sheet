package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.domain.model.Document
import javax.inject.Inject

class DocumentMapper @Inject constructor() {
    fun map(entity: DocumentEntity) = Document(
        id = entity.documentId,
        name = entity.name ?: "",
        text = entity.text ?: "",
    )
}