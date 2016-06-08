package software.renato.finances.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import software.renato.finances.model.FinancialRecord
import software.renato.finances.model.RecordType
import software.renato.finances.repository.FinancialRecordRepository
import software.renato.finances.util.toYearMonth
import java.io.InputStream
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@Service
class ImportFileService @Autowired constructor(val financialRecordRepository: FinancialRecordRepository) {

    fun import(inputStream: InputStream) {
        val financialRecords = readFinancialRecords(inputStream)
        financialRecordRepository.save(financialRecords)
    }

    private fun readFinancialRecords(inputStream: InputStream): List<FinancialRecord> {
        val financialRecords: MutableList<FinancialRecord> = mutableListOf()

        inputStream.use {
            val document = Jsoup.parse(it, "UTF-8", "")
            val rows = document.select("table tr")
            for (i in 1..rows.size - 1) {
                val row = rows.get(i)
                val financialRecord = readFinancialRecord(row)
                financialRecords.add(financialRecord)
            }
        }

        return financialRecords
    }

    private fun readFinancialRecord(row: Element): FinancialRecord {
        val strDate = row.child(0).text().trim('\u00a0')
        val strValue = row.child(4).text().replace(',', '.')

        val date = stringToDate(strDate)
        val description = row.child(2).text()
        val value = BigDecimal(strValue)
        val yearMonth = date.toYearMonth()

        return FinancialRecord(
                value.abs(),
                date,
                yearMonth.monthValue,
                yearMonth.year,
                description,
                getRecordType(value)
        )
    }

    private fun getRecordType(value: BigDecimal): RecordType {
        return if (value.compareTo(BigDecimal.ZERO) >= 0) RecordType.INCOME else RecordType.OUTCOME
    }

    private fun stringToDate(str: String): Date {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.parse(str)
    }

}
