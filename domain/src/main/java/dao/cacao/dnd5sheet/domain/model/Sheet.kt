package dao.cacao.dnd5sheet.domain.model

data class Sheet(
    val id: Long,
    val character: Character,
    val characterRace: Race?,
    val characterClass: CharacterClass?,
    val abilities: List<Ability>,
//    val savingThrows: List<Boolean>,
    val skills: List<Skill>,
//    val passivePerception: Int,
//    val armorClass: Int,
//    val hitDice: Int,
//    val maxHp: Int,
//    val tempHp: Int,
//    val currentHitPoInts: Int,
//    val initiative: Int,
//    val speed: Int,
//     val deathSaves,
//     val attackAndSpellCasting,
//     val featuresAndTraits,
//     val inventoryAndEquipment,
//     val otherProficienciesAndLanguages
)