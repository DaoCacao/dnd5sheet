package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao : BaseDao<ClassEntity> {

    @Query("SELECT * FROM class")
    fun getAll(): Flow<List<ClassEntity>>

    @Query("SELECT * FROM class WHERE class_id == :classId")
    fun getById(classId: Long): Flow<ClassEntity>
}

