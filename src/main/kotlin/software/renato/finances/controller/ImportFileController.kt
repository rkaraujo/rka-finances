package software.renato.finances.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import software.renato.finances.service.ImportFileService

@Controller
class ImportFileController @Autowired constructor(val importFileService: ImportFileService) {

    @RequestMapping(value = "/importFile.html", method = arrayOf(RequestMethod.POST))
    fun import(@RequestParam("file") file: MultipartFile): String {
        importFileService.import(file.inputStream)
        return "redirect:/finances.html"
    }

}
