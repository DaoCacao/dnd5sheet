package dao.cacao.dnd5sheet.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.dao.RaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity

@Database(
    version = 1,
    entities = [
        SheetEntity::class,
        RaceEntity::class,
    ],
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2),
//        AutoMigration(from = 2, to = 3),
//    ],
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sheetDao(): SheetDao
    abstract fun raceDao(): RaceDao
}

