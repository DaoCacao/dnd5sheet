package dao.cacao.dnd5sheet.domain.model.players_handbook

import dao.cacao.dnd5sheet.domain.model.Ability

sealed class PlayersHandbookTrait {
    class AbilityScoreIncrease(
        val ability: Ability,
        val value: Int,
    ) : PlayersHandbookTrait()

    class Age(
        val age: Int,
    ) : PlayersHandbookTrait()

    class Size(
        val size: Int,
    ) : PlayersHandbookTrait()

    class Speed(
        val speed: Int,
    ) : PlayersHandbookTrait()

    class DraconicAncestry(
        val dragonType: PlayersHandbookDragonType,
        val damageType: PlayersHandbookDamageType,
        val attack: PlayersHandbookAttack,
    ) : PlayersHandbookTrait()
}