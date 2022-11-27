package dao.cacao.dnd5sheet.data.storage.local.room.prepopulation

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookRace
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
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
        for (race in PlayersHandbookRace.values()) {
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
            PlayersHandbookRace.Halfling -> "document_race_halfling.md"
            PlayersHandbookRace.Human -> "document_race_human.md"
            PlayersHandbookRace.Dragonborn -> "document_race_dragonborn.md"
            PlayersHandbookRace.Gnome -> "document_race_gnome.md"
            PlayersHandbookRace.HalfElf -> "document_race_half_elf.md"
            PlayersHandbookRace.HalfOrk -> "document_race_half_orc.md"
            PlayersHandbookRace.Tiefling -> "document_race_tiefling.md"
        }
        return DocumentEntity(
            name = race.name,
            text = context.assets.open(fileName)
                .bufferedReader()
                .use(BufferedReader::readText)
        )
    }

    private fun createPlayersHandbookRace(race: PlayersHandbookRace): RaceEntity {
        return RaceEntity(
            name = race.name,
        )
    }
}