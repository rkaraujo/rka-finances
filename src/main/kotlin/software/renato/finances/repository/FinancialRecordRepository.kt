package software.renato.finances.repository

import org.springframework.data.repository.CrudRepository
import software.renato.finances.model.FinancialRecord

interface FinancialRecordRepository : CrudRepository<FinancialRecord, Long> {

    fun findByMonthAndYearOrderByDate(month: Int, year: Int) : List<FinancialRecord>

}


