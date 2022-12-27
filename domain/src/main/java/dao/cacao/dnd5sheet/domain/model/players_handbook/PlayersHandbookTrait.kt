package dao.cacao.dnd5sheet.domain.model.players_handbook

sealed class PlayersHandbookTrait {
    class AbilityScoreIncrease(
        val race: PlayersHandbookRace,
        val ability: PlayersHandbookAbility,
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