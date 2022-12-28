package dao.cacao.dnd5sheet.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dao.cacao.dnd5sheet.data.repository.AbilityRepositoryImpl
import dao.cacao.dnd5sheet.data.repository.ClassRepositoryImpl
import dao.cacao.dnd5sheet.data.repository.DocumentRepositoryImpl
import dao.cacao.dnd5sheet.data.repository.RaceRepositoryImpl
import dao.cacao.dnd5sheet.data.repository.SheetRepositoryImpl
import dao.cacao.dnd5sheet.data.repository.SkillRepositoryImpl
import dao.cacao.dnd5sheet.data.repository.SubraceRepositoryImpl
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import dao.cacao.dnd5sheet.domain.boundary.DocumentRepository
import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.boundary.SubraceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun sheetRepository(impl: SheetRepositoryImpl): SheetRepository

    @Binds
    @Singleton
    fun raceRepository(impl: RaceRepositoryImpl): RaceRepository

    @Binds
    @Singleton
    fun subRaceRepository(impl: SubraceRepositoryImpl): SubraceRepository

    @Binds
    @Singleton
    fun classRepository(impl: ClassRepositoryImpl): ClassRepository

    @Binds
    @Singleton
    fun abilityRepository(impl: AbilityRepositoryImpl): AbilityRepository

    @Binds
    @Singleton
    fun skillRepository(impl: SkillRepositoryImpl): SkillRepository

    @Binds
    @Singleton
    fun documentRepository(impl: DocumentRepositoryImpl): DocumentRepository
}