package software.renato.finances.util

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.util.*

fun LocalDate.toDate(): Date {
    return Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
}

fun Date.toYearMonth(): YearMonth {
    return YearMonth.from(this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
}
