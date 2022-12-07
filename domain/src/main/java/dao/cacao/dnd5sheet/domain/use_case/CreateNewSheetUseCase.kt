package dao.cacao.dnd5sheet.domain.use_case

import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.boundary.SkillRepository
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookAbility
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookSkill
import javax.inject.Inject

class CreateNewSheetUseCase @Inject constructor(
    private val sheetRepository: SheetRepository,
    private val abilityRepository: AbilityRepository,
    private val skillRepository: SkillRepository,
) : suspend () -> Long {
    override suspend fun invoke(): Long {
        val sheetId = sheetRepository.createSheet()

        val abilityToId = PlayersHandbookAbility.values()
            .associateWith { abilityRepository.createAbility(it, sheetId) }

        val skillToId = PlayersHandbookSkill.values()
            .associateWith {
                val abilityId = abilityToId[it.ability] ?: error("Ability and skill must be associated")
                skillRepository.createSkill(it, sheetId, abilityId)
            }
        return sheetId
    }
}