package dao.cacao.dnd5sheet.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.dao.AbilityDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.CharacterDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.ClassDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.DocumentDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.RaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetToClassDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetToRaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SkillDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.AbilityEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.CharacterEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SkillEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref.SheetToClassCrossRef
import dao.cacao.dnd5sheet.data.storage.local.room.model.cross_ref.SheetToRaceCrossRef

@Database(
    entities = [
        // Entities
        SheetEntity::class,
        CharacterEntity::class,
        RaceEntity::class,
        ClassEntity::class,
        AbilityEntity::class,
        SkillEntity::class,
        DocumentEntity::class,
        // Cross refs
        SheetToRaceCrossRef::class,
        SheetToClassCrossRef::class,
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = [
    ],
)
abstract class AppDatabase : RoomDatabase() {
    // Entities
    abstract fun sheetDao(): SheetDao
    abstract fun characterDao(): CharacterDao
    abstract fun raceDao(): RaceDao
    abstract fun classDao(): ClassDao
    abstract fun abilityDao(): AbilityDao
    abstract fun skillDao(): SkillDao
    abstract fun documentDao(): DocumentDao

    // Cross refs
    abstract fun sheetToRaceDao(): SheetToRaceDao
    abstract fun sheetToClassDao(): SheetToClassDao
}