package dao.cacao.dnd5sheet.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.dao.AbilityDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.ClassDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.DocumentDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.RaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.AbilityEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity

@Database(
    version = 1,
    entities = [
        SheetEntity::class,
        AbilityEntity::class,

        RaceEntity::class,
        ClassEntity::class,
        DocumentEntity::class,
    ],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sheetDao(): SheetDao
    abstract fun abilityDao(): AbilityDao

    abstract fun raceDao(): RaceDao
    abstract fun classDao(): ClassDao
    abstract fun documentDao(): DocumentDao
}

