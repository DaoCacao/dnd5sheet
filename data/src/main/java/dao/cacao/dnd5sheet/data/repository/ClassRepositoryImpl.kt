package dao.cacao.dnd5sheet.data.repository

import dao.cacao.dnd5sheet.data.storage.local.ClassLocalStorage
import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import javax.inject.Inject

class ClassRepositoryImpl @Inject constructor(
    private val classLocalStorage: ClassLocalStorage,
) : ClassRepository {
    override fun getClasses() = classLocalStorage.getClasses()
}