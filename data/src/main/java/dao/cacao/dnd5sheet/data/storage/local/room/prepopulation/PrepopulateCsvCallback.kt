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
import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookSubraceEntity
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
                database.get().playersHandbookSubraceDao()
                    .insert("prepopulation/Dnd 5 Players handbook - Subrace.csv".asCsv().mapSubrace())
                database.get().playersHandbookClassDao()
                    .insert("prepopulation/Dnd 5 Players handbook - Class.csv".asCsv().mapClass())
                database.get().playersHandbookAbilityDao()
                    .insert("prepopulation/Dnd 5 Players handbook - Ability.csv".asCsv().mapAbility())
                database.get().playersHandbookAbilityIncreaseDao()
                    .insert("prepopulation/Dnd 5 Players handbook - AbilityIncrease.csv".asCsv().mapAbilityIncrease())
            }
        }
    }

    private fun String.asCsv(): List<List<String>> {
        return context.assets.open(this)
            .reader()
            .readLines()
            // ignore column names
            .drop(1)
            // split rows by ','
            .map { it.split(',') }
    }

    private fun List<List<String>>.mapRace(): List<PlayersHandbookRaceEntity> {
        return map {
            PlayersHandbookRaceEntity(
                raceId = it[0],
                name = it[1],
            )
        }
    }

    private fun List<List<String>>.mapSubrace(): List<PlayersHandbookSubraceEntity> {
        return map {
            PlayersHandbookSubraceEntity(
                subraceId = it[0],
                raceId = it[1],
                name = it[2],
            )
        }
    }

    private fun List<List<String>>.mapClass(): List<PlayersHandbookClassEntity> {
        return map {
            PlayersHandbookClassEntity(
                classId = it[0],
                name = it[1],
            )
        }
    }

    private fun List<List<String>>.mapAbility(): List<PlayersHandbookAbilityEntity> {
        return map {
            PlayersHandbookAbilityEntity(
                abilityId = it[0],
                name = it[1],
            )
        }
    }

    private fun List<List<String>>.mapAbilityIncrease(): List<PlayersHandbookAbilityIncreaseEntity> {
        return map {
            PlayersHandbookAbilityIncreaseEntity(
                abilityId = it[0],
                raceId = it[1],
                subRaceId = it[2],
                value = it[3].toInt(),
            )
        }
    }
}