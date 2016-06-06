package software.renato.finances.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import software.renato.finances.model.FinancialRecord
import software.renato.finances.model.RecordType
import software.renato.finances.repository.FinancialRecordRepository
import software.renato.finances.util.toDate
import java.time.LocalDate

@Controller
class FinancesController @Autowired constructor(val financialRecordRepository: FinancialRecordRepository) {

    @RequestMapping(value = "/finances.html", method = arrayOf(RequestMethod.GET))
    fun finances(model: Model) : String {
        model.addAttribute("financesForm", FinancesForm())
        return "finances"
    }

    @RequestMapping(value = "/finances.html", method = arrayOf(RequestMethod.POST))
    fun save(financesForm: FinancesForm) : String {
        val financialRecords: List<FinancialRecord> = createFinancialRecords(financesForm)
        financialRecordRepository.save(financialRecords)
        return "redirect:/finances.html"
    }

    private fun createFinancialRecords(financesForm: FinancesForm): List<FinancialRecord> {
        val financialRecords: MutableList<FinancialRecord> = mutableListOf()

        for (i in financesForm.expenseDay.indices) {
            val expenseRecord = FinancialRecord(
                    date = LocalDate.of(2016, 5, financesForm.expenseDay[i]).toDate(),
                    month = 5,
                    year = 2016,
                    value = financesForm.expenseValue[i],
                    description = financesForm.expenseDescription[i],
                    type = RecordType.OUTCOME
            )
            financialRecords.add(expenseRecord)
        }

        for (i in financesForm.incomeDay.indices) {
            val incomeRecord = FinancialRecord(
                    date = LocalDate.of(2016, 5, financesForm.incomeDay[i]).toDate(),
                    month = 5,
                    year = 2016,
                    value = financesForm.incomeValue[i],
                    description = financesForm.incomeDescription[i],
                    type = RecordType.INCOME
            )
            financialRecords.add(incomeRecord)
        }

        return financialRecords
    }

}
