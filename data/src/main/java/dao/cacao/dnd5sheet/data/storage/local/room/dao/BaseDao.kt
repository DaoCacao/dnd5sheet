package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    suspend fun insert(entity: T): Long

    @Insert
    suspend fun insert(entities: List<T>): List<Long>

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(entity: List<T>)
//
//    @Delete
//    suspend fun delete(vararg entity: T)
}