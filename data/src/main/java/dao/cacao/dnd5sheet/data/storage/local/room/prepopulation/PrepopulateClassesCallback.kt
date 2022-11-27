package dao.cacao.dnd5sheet.data.storage.local.room.prepopulation

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.model.ClassEntity
import dao.cacao.dnd5sheet.data.storage.local.room.model.DocumentEntity
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookClass
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.util.concurrent.Executors
import javax.inject.Inject

class PrepopulateClassesCallback @Inject constructor(
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
        for (clazz in PlayersHandbookClass.values()) {
//            val documentEntity = createPlayersHandbookClassDocument(clazz)
            val classEntity = createPlayersHandbookClass(clazz)

//            val documentId = database.get().documentDao().insert(documentEntity)
            database.get().classDao().insert(classEntity)
        }
    }

    private fun createPlayersHandbookClassDocument(clazz: PlayersHandbookClass): DocumentEntity {
        val fileName = when (clazz) {
            PlayersHandbookClass.Barbarian -> ""
            PlayersHandbookClass.Bard -> ""
            PlayersHandbookClass.Cleric -> ""
            PlayersHandbookClass.Druid -> ""
            PlayersHandbookClass.Fighter -> ""
            PlayersHandbookClass.Monk -> ""
            PlayersHandbookClass.Paladin -> ""
            PlayersHandbookClass.Ranger -> ""
            PlayersHandbookClass.Rogue -> ""
            PlayersHandbookClass.Sorcerer -> ""
            PlayersHandbookClass.Warlock -> ""
            PlayersHandbookClass.Wizard -> ""
        }
        return DocumentEntity(
            name = clazz.name,
            text = context.assets.open(fileName)
                .bufferedReader()
                .use(BufferedReader::readText)
        )
    }

    private fun createPlayersHandbookClass(clazz: PlayersHandbookClass): ClassEntity {
        return ClassEntity(
            name = clazz.name,
        )
    }
}