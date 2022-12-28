package dao.cacao.dnd5sheet.data.storage.local.room.prepopulation

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityIncreaseEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookRaceEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubRaceEntity
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import javax.inject.Inject

class PrepopulateCsvCallback @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: dagger.Lazy<AppDatabase>,
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        Executors.newSingleThreadScheduledExecutor().execute {
            runBlocking {
                database.get().playersHandbookRaceDao()
                    .insert("prepopulation/Dnd 5 Players handbook - Race.csv".asCsv().mapRace())
                database.get().playersHandbookSubRaceDao()
                    .insert("prepopulation/Dnd 5 Players handbook - SubRace.csv".asCsv().mapSubRace())
                database.get().playersHandbookClassDao()
                    .insert("prepopulation/Dnd 5 Players handbook - Class.csv".asCsv().mapClass())
                database.get().playersHandbookAbilityDao()
                    .insert("prepopulation/Dnd 5 Players handbook - Ability.csv".asCsv().mapAbility())
                database.get().playersHandbookAbilityIncreaseDao()
                    .insert("prepopulation/Dnd 5 Players handbook - AbilityIncrease.csv".asCsv().mapAbilityIncrease())
            }
        }
    }

    private fun String.asCsv(): List<String> {
        return context.assets.open(this)
            .reader()
            .readLines()
            // ignore column names
            .drop(1)
            // split rows by ','
            .map { it.split(',') }
            .flatten()
    }

    private fun List<String>.mapRace(): MutableList<PlayersHandbookRaceEntity> {
        val iterator = iterator()
        val entities = mutableListOf<PlayersHandbookRaceEntity>()
        while (iterator.hasNext()) {
            entities += PlayersHandbookRaceEntity(
                raceId = iterator.next(),
                name = iterator.next(),
            )
        }
        return entities
    }

    private fun List<String>.mapSubRace(): MutableList<PlayersHandbookSubRaceEntity> {
        val iterator = iterator()
        val entities = mutableListOf<PlayersHandbookSubRaceEntity>()
        while (iterator.hasNext()) {
            entities += PlayersHandbookSubRaceEntity(
                subRaceId = iterator.next(),
                raceId = iterator.next(),
                name = iterator.next(),
            )
        }
        return entities
    }

    private fun List<String>.mapClass(): MutableList<PlayersHandbookClassEntity> {
        val iterator = iterator()
        val entities = mutableListOf<PlayersHandbookClassEntity>()
        while (iterator.hasNext()) {
            entities += PlayersHandbookClassEntity(
                classId = iterator.next(),
                name = iterator.next(),
            )
        }
        return entities
    }

    private fun List<String>.mapAbility(): MutableList<PlayersHandbookAbilityEntity> {
        val iterator = iterator()
        val entities = mutableListOf<PlayersHandbookAbilityEntity>()
        while (iterator.hasNext()) {
            entities += PlayersHandbookAbilityEntity(
                abilityId = iterator.next(),
                name = iterator.next(),
            )
        }
        return entities
    }

    private fun List<String>.mapAbilityIncrease(): MutableList<PlayersHandbookAbilityIncreaseEntity> {
        val iterator = iterator()
        val entities = mutableListOf<PlayersHandbookAbilityIncreaseEntity>()
        while (iterator.hasNext()) {
            entities += PlayersHandbookAbilityIncreaseEntity(
                abilityId = iterator.next(),
                raceId = iterator.next(),
                subRaceId = iterator.next(),
                value = iterator.next().toInt(),
            )
        }
        return entities
    }
}