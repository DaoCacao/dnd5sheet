package dao.cacao.dnd5sheet.presentation.screen.sheet

data class SheetState(
    val isLoading: Boolean,
    val page: SheetScreenPages,
    val name: String,
    val characterRace: String,
    val characterClass: String,
    val level: Int,
    val proficiencyBonus: Int,
    val abilities: List<AbilityItem>,
    val skills: List<SkillItem>,
) {
    enum class SheetScreenPages {
        Common,
        Abilities,
        Skills,
    }

    data class AbilityItem(
        val id: Long,
        val name: String,
        val score: Int,
        val modifier: Int?,
    )

    data class SkillItem(
        val id: Long,
        val abilityId: Long,
        val name: String,
        val abilityName: String,
        val modifier: Int?,
        val proficiency: Boolean,
    )

    companion object {
        fun loading() = SheetState(
            isLoading = true,
            page = SheetScreenPages.Common,
            name = "",
            characterRace = "",
            characterClass = "",
            level = 0,
            proficiencyBonus = 0,
            abilities = emptyList(),
            skills = emptyList()
        )

        fun content(
            page: SheetScreenPages,
            name: String,
            characterRace: String,
            characterClass: String,
            level: Int,
            proficiencyBonus: Int,
            abilities: List<AbilityItem>,
            skills: List<SkillItem>,
        ) = SheetState(
            isLoading = false,
            page = page,
            name = name,
            characterRace = characterRace,
            characterClass = characterClass,
            level = level,
            proficiencyBonus = proficiencyBonus,
            abilities = abilities,
            skills = skills,
        )
    }
}