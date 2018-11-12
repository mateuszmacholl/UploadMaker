package mateuszmacholl.uploadmaker.validation.file.uniqueFileNameInList

import mateuszmacholl.uploadmaker.dto.FileDto
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UniqueFileNameOnListValidator : ConstraintValidator<UniqueFileNameOnList, List<FileDto>> {
    override fun initialize(constraintAnnotation: UniqueFileNameOnList) {
    }

    override fun isValid(files: List<FileDto>,
                         context: ConstraintValidatorContext): Boolean {

        context.disableDefaultConstraintViolation()
        val result = files.groupingBy { it.name }.eachCount().filter { it.value > 1 }
        if (result.isNotEmpty()) {
            context.buildConstraintViolationWithTemplate("Name must be unique on list")
                    .addConstraintViolation()
            return false
        }
        return true
    }
}