package dao.cacao.dnd5sheet.data.storage.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref.SheetToClassCrossRef

@Dao
interface SheetToClassDao : BaseDao<SheetToClassCrossRef> {

    @Query("DELETE FROM sheet_to_class WHERE sheet_id == :sheetId")
    suspend fun deleteBySheetId(sheetId: Long)
}

