package dao.cacao.dnd5sheet.domain.use_case.calculation

import javax.inject.Inject

class CalculateAbilityModifierUseCase @Inject constructor() : (Int) -> Int? {
    override fun invoke(abilityScore: Int): Int? {
        return when (abilityScore) {
            1 -> -5
            2, 3 -> -4
            4, 5 -> -3
            6, 7 -> -2
            8, 9 -> -1
            10, 11 -> 0
            12, 13 -> 1
            14, 15 -> 2
            16, 17 -> 3
            18, 19 -> 4
            20, 21 -> 5
            22, 23 -> 6
            24, 25 -> 7
            26, 27 -> 8
            28, 29 -> 9
            30 -> 10
            else -> null
        }
    }
}