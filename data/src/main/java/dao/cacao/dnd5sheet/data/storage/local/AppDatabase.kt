package dao.cacao.dnd5sheet.data.storage.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        SheetEntity::class,
    ],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sheetDao(): SheetDao
}

