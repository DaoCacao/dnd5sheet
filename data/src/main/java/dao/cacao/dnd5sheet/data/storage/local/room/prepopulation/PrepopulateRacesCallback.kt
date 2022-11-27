package dao.cacao.dnd5sheet.data.storage.local.room.prepopulation

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.RaceEntity
import dao.cacao.dnd5sheet.domain.model.race.PlayersHandbookRace
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import javax.inject.Inject

class PrepopulateRacesCallback @Inject constructor(
    private val database: dagger.Lazy<AppDatabase>,
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        Executors.newSingleThreadScheduledExecutor().execute {
            runBlocking {
                database.get().raceDao().insert(
                    createPlayersHandbookRaces()
                )
            }
        }
    }

    private fun createPlayersHandbookRaces(): List<RaceEntity> {
        return PlayersHandbookRace.values().map {
            RaceEntity(
                name = it.name,
            )
        }
    }
}