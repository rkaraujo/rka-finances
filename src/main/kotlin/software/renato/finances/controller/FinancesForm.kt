package software.renato.finances.controller

import java.math.BigDecimal

data class FinancesForm(

        var expenseId: LongArray = longArrayOf(),
        var expenseDay: IntArray = intArrayOf(),
        var expenseValue: Array<BigDecimal> = emptyArray(),
        var expenseDescription: Array<String?> = emptyArray(),

        var incomeId: LongArray = longArrayOf(),
        var incomeDay: IntArray = intArrayOf(),
        var incomeValue: Array<BigDecimal> = emptyArray(),
        var incomeDescription: Array<String?> = emptyArray()

)
