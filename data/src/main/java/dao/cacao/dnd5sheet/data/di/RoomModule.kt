package dao.cacao.dnd5sheet.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dao.cacao.dnd5sheet.data.storage.local.room.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    fun database(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db")
            .build()
    }
}