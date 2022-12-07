package dao.cacao.dnd5sheet.domain.model

data class Skill(
    val id: Long,
    val abilityId: Long,
    val name: String,
    val proficiency: Boolean,
)