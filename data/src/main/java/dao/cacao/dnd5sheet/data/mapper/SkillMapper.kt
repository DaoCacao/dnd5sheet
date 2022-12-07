package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.SkillEntity
import dao.cacao.dnd5sheet.domain.model.Skill
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookSkill

fun SkillEntity.map() = Skill(
    id = this.skillId,
    abilityId = this.abilityId,
    name = this.name ?: "",
    proficiency = this.proficiency ?: false,
)

fun PlayersHandbookSkill.map(sheetId: Long, abilityId: Long) = SkillEntity(
    sheetId = sheetId,
    abilityId = abilityId,
    name = this.name,
)