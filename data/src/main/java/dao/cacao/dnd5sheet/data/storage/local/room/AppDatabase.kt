package dao.cacao.dnd5sheet.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dao.cacao.dnd5sheet.data.storage.local.room.converter.Converters
import dao.cacao.dnd5sheet.data.storage.local.room.dao.ClassDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.DocumentDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.RaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity

@Database(
    version = 1,
    entities = [
        SheetEntity::class,
        RaceEntity::class,
        ClassEntity::class,
        DocumentEntity::class,
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sheetDao(): SheetDao
    abstract fun raceDao(): RaceDao
    abstract fun classDao(): ClassDao
    abstract fun documentDao(): DocumentDao
}

