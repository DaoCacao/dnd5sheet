package dao.cacao.dnd5sheet.domain.use_case.calculation

import javax.inject.Inject

class CalculateLevelUseCase @Inject constructor() {
    operator fun invoke(experience: Int): Int? {
        return when (experience) {
            0 -> 1
            300 -> 2
            900 -> 3
            2_700 -> 4
            6_500 -> 5
            14_000 -> 6
            23_000 -> 7
            34_000 -> 8
            48_000 -> 9
            64_000 -> 10
            85_000 -> 11
            100_000 -> 12
            120_000 -> 13
            140_000 -> 14
            165_000 -> 15
            195_000 -> 16
            225_000 -> 17
            265_000 -> 18
            305_000 -> 19
            355_000 -> 20
            else -> null
        }
    }
}