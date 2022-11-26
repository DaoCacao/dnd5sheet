package dao.cacao.dnd5sheet.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity

@Database(
    version = 1,
    entities = [
        SheetEntity::class,
    ],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sheetDao(): SheetDao
}

