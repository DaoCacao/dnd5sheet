package dao.cacao.dnd5sheet.domain.model.players_handbook

enum class PlayersHandbookSkill(
    val ability: PlayersHandbookAbility,
) {
    Acrobatics(PlayersHandbookAbility.Dexterity),
    AnimalHandling(PlayersHandbookAbility.Wisdom),
    Arcana(PlayersHandbookAbility.Intelligence),
    Athletics(PlayersHandbookAbility.Strength),
    Deception(PlayersHandbookAbility.Charisma),
    History(PlayersHandbookAbility.Intelligence),
    Insight(PlayersHandbookAbility.Wisdom),
    Intimidation(PlayersHandbookAbility.Charisma),
    Investigation(PlayersHandbookAbility.Intelligence),
    Medicine(PlayersHandbookAbility.Wisdom),
    Nature(PlayersHandbookAbility.Intelligence),
    Perception(PlayersHandbookAbility.Wisdom),
    Performance(PlayersHandbookAbility.Charisma),
    Persuasion(PlayersHandbookAbility.Charisma),
    Religion(PlayersHandbookAbility.Intelligence),
    SleightOfHand(PlayersHandbookAbility.Dexterity),
    Stealth(PlayersHandbookAbility.Dexterity),
    Survival(PlayersHandbookAbility.Wisdom),
}