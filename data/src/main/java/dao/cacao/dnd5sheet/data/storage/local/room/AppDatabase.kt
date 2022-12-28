package dao.cacao.dnd5sheet.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.dao.AbilityDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.DocumentDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SheetDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.SkillDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook.PlayersHandbookAbilityDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook.PlayersHandbookAbilityIncreaseDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook.PlayersHandbookClassDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook.PlayersHandbookRaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.dao.players_handbook.PlayersHandbookSubRaceDao
import dao.cacao.dnd5sheet.data.storage.local.room.model.AbilityEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SheetEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.SkillEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityIncreaseEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookRaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubRaceEntity

@Database(
    entities = [
        // PlayersHandbook
        PlayersHandbookRaceEntity::class,
        PlayersHandbookSubRaceEntity::class,
        PlayersHandbookClassEntity::class,
        PlayersHandbookAbilityEntity::class,
        PlayersHandbookAbilityIncreaseEntity::class,

        // Entities
        SheetEntity::class,
        AbilityEntity::class,
        SkillEntity::class,
        DocumentEntity::class,
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = [
//        AutoMigration(from = 1, to = 2),
    ],
)
abstract class AppDatabase : RoomDatabase() {
    // PlayersHandbook
    abstract fun playersHandbookRaceDao(): PlayersHandbookRaceDao
    abstract fun playersHandbookSubRaceDao(): PlayersHandbookSubRaceDao
    abstract fun playersHandbookClassDao(): PlayersHandbookClassDao
    abstract fun playersHandbookAbilityDao(): PlayersHandbookAbilityDao
    abstract fun playersHandbookAbilityIncreaseDao(): PlayersHandbookAbilityIncreaseDao

    // Entities
    abstract fun sheetDao(): SheetDao
    abstract fun abilityDao(): AbilityDao
    abstract fun skillDao(): SkillDao
    abstract fun documentDao(): DocumentDao
}