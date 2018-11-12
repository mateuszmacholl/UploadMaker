package mateuszmacholl.uploadmaker.validation.file.uniqueFileName

import mateuszmacholl.uploadmaker.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UniqueFileNameValidator : ConstraintValidator<UniqueFileName, String> {

    @Autowired
    lateinit var fileService: FileService

    override fun initialize(constraint: UniqueFileName) {}

    override fun isValid(name: String, context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()
        if (fileService.findByName(name) != null) {
            context.buildConstraintViolationWithTemplate("already exists file with such name")
                    .addConstraintViolation()
            return false
        }
        return true
    }
}

