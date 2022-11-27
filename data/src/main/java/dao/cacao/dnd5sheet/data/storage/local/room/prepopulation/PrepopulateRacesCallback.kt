package dao.cacao.dnd5sheet.data.storage.local.room.prepopulation

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.domain.model.race.PlayersHandbookRace
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import javax.inject.Inject

class PrepopulateRacesCallback @Inject constructor(
    @ApplicationContext private val context: Context,
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
        val races = listOf(
            PlayersHandbookRace.Dragonborn,
            PlayersHandbookRace.Elf,
            PlayersHandbookRace.Dwarf,
        )
        for (race in races) {
            val documentEntity = createPlayersHandbookRaceDocument(race)
            val raceEntity = createPlayersHandbookRace(race)

            val documentId = database.get().documentDao().insert(documentEntity)
            database.get().raceDao().insert(raceEntity.copy(documentId = documentId))
        }
    }

    private fun createPlayersHandbookRaceDocument(race: PlayersHandbookRace): DocumentEntity {
        val fileName = when (race) {
            PlayersHandbookRace.Dwarf -> "document_race_dwarf.md"
            PlayersHandbookRace.Elf -> "document_race_elf.md"
            PlayersHandbookRace.Halfling -> ""
            PlayersHandbookRace.Human -> ""
            PlayersHandbookRace.Dragonborn -> "document_race_dragonborn.md"
            PlayersHandbookRace.Gnome -> ""
            PlayersHandbookRace.HalfElf -> ""
            PlayersHandbookRace.HalfOrk -> ""
            PlayersHandbookRace.Tiefling -> ""
        }
        return DocumentEntity(
            name = race.name,
            text = context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }
        )
    }

    private fun createPlayersHandbookRace(race: PlayersHandbookRace): RaceEntity {
        return RaceEntity(
            name = race.name,
        )
    }
}