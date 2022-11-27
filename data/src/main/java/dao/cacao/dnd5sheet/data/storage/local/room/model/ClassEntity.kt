package dao.cacao.dnd5sheet.data.storage.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class")
data class ClassEntity(
    @ColumnInfo(name = "class_id") @PrimaryKey(autoGenerate = true) val classId: Long = 0,
    @ColumnInfo(name = "document_id") val documentId: Long = 0,
    @ColumnInfo(name = "name") val name: String? = null,
)