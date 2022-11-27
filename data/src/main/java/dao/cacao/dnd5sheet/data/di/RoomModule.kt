package dao.cacao.dnd5sheet.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase
import dao.cacao.dnd5sheet.data.storage.local.room.prepopulation.PrepopulateClassesCallback
import dao.cacao.dnd5sheet.data.storage.local.room.prepopulation.PrepopulateRacesCallback
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun database(
        @ApplicationContext context: Context,
        prepopulateRacesCallback: PrepopulateRacesCallback,
        prepopulateClassesCallback: PrepopulateClassesCallback,
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db")
            .addCallback(prepopulateRacesCallback)
            .addCallback(prepopulateClassesCallback)
            .fallbackToDestructiveMigration()
            .build()
    }
}

