package dao.cacao.dnd5sheet.presentation.screen.sheet

sealed class SheetState {
    object Loading : SheetState()
    data class Content(
        val level: Int,
        val characterName: String,
        val characterRace: String,
        val characterClass: String,
        val proficiencyBonus: Int,
        val abilities: List<Ability>,
        val skills: List<Skill>,
    ) : SheetState() {
        data class Ability(
            val id: Long,
            val name: String,
            val score: Int,
            val modifier: Int?,
        )

        data class Skill(
            val id: Long,
            val abilityId: Long,
            val name: String,
            val ability: String,
            val modifier: Int?,
            val proficiency: Boolean,
        )
    }
}