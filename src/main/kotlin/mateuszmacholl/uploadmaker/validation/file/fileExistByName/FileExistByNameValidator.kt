package mateuszmacholl.uploadmaker.validation.file.fileExistByName

import mateuszmacholl.uploadmaker.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FileExistByNameValidator : ConstraintValidator<FileExistByName, String> {
    @Autowired
    lateinit var fileService: FileService

    override fun initialize(constraint: FileExistByName) {}

    override fun isValid(name: String, context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()
        if (fileService.findByName(name) == null) {
            context.buildConstraintViolationWithTemplate("There is no file with such name")
                    .addConstraintViolation()
            return false
        }
        return true
    }
}

