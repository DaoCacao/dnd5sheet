package dao.cacao.dnd5sheet.presentation.screen.sheet

object Sheet {
    data class State(
        val isLoading: Boolean = false,
        val page: SheetScreenPages = SheetScreenPages.Common,
        val name: String = "",
        val characterRace: String = "",
        val characterClass: String = "",
        val level: Int = 0,
        val proficiencyBonus: Int = 0,
        val abilities: List<AbilityItem> = emptyList(),
        val skills: List<SkillItem> = emptyList(),
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
    }

    sealed class Event {
        class NavigateToSelectName(val sheetId: Long, val name: String) : Event()
        class NavigateToSelectRace(val sheetId: Long) : Event()
        class NavigateToSelectClass(val sheetId: Long) : Event()
    }
}