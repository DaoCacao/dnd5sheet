package dao.cacao.dnd5sheet.domain.use_case.calculation

import javax.inject.Inject

class CalculateProficiencyBonusUseCase @Inject constructor() : (Int) -> Int? {
    override fun invoke(level: Int): Int? {
        return when (level) {
            1, 2, 3, 4 -> 2
            5, 6, 7, 8 -> 3
            9, 10, 11, 12 -> 4
            13, 14, 15, 16 -> 5
            17, 18, 19, 20 -> 6
            else -> null
        }
    }
}