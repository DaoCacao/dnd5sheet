package dao.cacao.dnd5sheet.data.mapper

import dao.cacao.dnd5sheet.data.storage.local.room.model.players_handbook.PlayersHandbookAbilityEntity
import dao.cacao.dnd5sheet.domain.model.Ability

fun PlayersHandbookAbilityEntity.map() = Ability(
    id = this.abilityId,
    name = this.name,
)