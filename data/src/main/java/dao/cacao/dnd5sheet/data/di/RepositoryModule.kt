package dao.cacao.dnd5sheet.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dao.cacao.dnd5sheet.data.repository.SheetRepositoryImpl
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun sheetRepository(impl: SheetRepositoryImpl): SheetRepository
}