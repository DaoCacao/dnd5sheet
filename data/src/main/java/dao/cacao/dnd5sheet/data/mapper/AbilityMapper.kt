package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.AbilityEntity
import dao.cacao.dnd5sheet.domain.model.Ability
import dao.cacao.dnd5sheet.domain.model.players_handbook.PlayersHandbookAbility

fun AbilityEntity.map() = Ability(
    id = this.abilityId,
    name = this.name ?: "",
    score = this.score ?: 0,
)

fun PlayersHandbookAbility.map(sheetId: Long) = AbilityEntity(
    sheetId = sheetId,
    name = this.name,
    score = 0,
)