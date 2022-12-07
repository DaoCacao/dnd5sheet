package dao.cacao.dnd5sheet.domain.use_case.calculation

import javax.inject.Inject

class CalculateSkillModifierUseCase @Inject constructor(
    private val calculateAbilityModifier: CalculateAbilityModifierUseCase,
) : (Int, Int, Boolean) -> Int? {
    override fun invoke(abilityScore: Int, proficiencyBonus: Int, hasProficiency: Boolean): Int? {
        val abilityModifier = calculateAbilityModifier(abilityScore) ?: return null
        return abilityModifier + if (hasProficiency) proficiencyBonus else 0
    }
}

