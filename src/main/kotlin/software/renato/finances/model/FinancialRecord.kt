package software.renato.finances.model

import java.math.BigDecimal
import java.time.YearMonth
import java.util.*
import javax.persistence.*

@Entity
@Table(name="financial_record")
data class FinancialRecord(
        var value: BigDecimal = BigDecimal.ZERO,

        var date: Date = Date(),

        var month: Int = YearMonth.now().monthValue,

        var year: Int = YearMonth.now().year,

        var description: String? = null,

        @Enumerated(EnumType.STRING)
        var type: RecordType = RecordType.OUTCOME,

        @Id
        @GeneratedValue
        var id: Long = 0
)


