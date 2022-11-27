package dao.cacao.dnd5sheet.data.storage.local.room.prepopulation

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import androidx.sqlite.db.SupportSQLiteDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.domain.model.race.PlayersHandbookRace
import kotlinx.coroutines.runBlocking
import java.io.FileInputStream
import java.util.concurrent.Executors
import javax.inject.Inject

class PrepopulateRacesCallback @Inject constructor(
    private val database: dagger.Lazy<AppDatabase>,
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        Executors.newSingleThreadScheduledExecutor().execute {
            runBlocking {
                populatePlayersHandbookEntities()
            }
        }
    }

    private suspend fun populatePlayersHandbookEntities() {
        val race = PlayersHandbookRace.Dragonborn
        val documentEntity = createPlayersHandbookRaceDocument(race)
        val raceEntity = createPlayersHandbookRace(race)
//        database.get().withTransaction {
            val documentId = database.get().documentDao().insert(documentEntity)
            database.get().raceDao().insert(raceEntity.copy(documentId = documentId))
//        }
    }


    private fun createPlayersHandbookRaceDocument(race: PlayersHandbookRace): DocumentEntity {
        val text = when (race) {
            PlayersHandbookRace.Dwarf -> ""
            PlayersHandbookRace.Elf -> ""
            PlayersHandbookRace.Halfling -> ""
            PlayersHandbookRace.Human -> ""
            PlayersHandbookRace.Dragonborn -> FileInputStream("document_race_dragonborn.md").use {
                it.bufferedReader().readText()
            }
            PlayersHandbookRace.Gnome -> ""
            PlayersHandbookRace.HalfElf -> ""
            PlayersHandbookRace.HalfOrk -> ""
            PlayersHandbookRace.Tiefling -> ""
        }
        return DocumentEntity(
            name = race.name,
            text = text,
        )
    }

    private fun createPlayersHandbookRace(race: PlayersHandbookRace): RaceEntity {
        val name = race.name
        return RaceEntity(
            name = name,
        )
    }
}