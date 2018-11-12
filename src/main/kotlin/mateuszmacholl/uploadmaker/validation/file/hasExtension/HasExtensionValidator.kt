package mateuszmacholl.uploadmaker.validation.file.hasExtension

import org.apache.commons.io.FilenameUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class HasExtensionValidator : ConstraintValidator<HasExtension, String> {

    override fun initialize(constraint: HasExtension) {}

    override fun isValid(name: String, context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()
        if (!hasExtension(name)) {
            context.buildConstraintViolationWithTemplate("must has an extension")
                    .addConstraintViolation()
            return false
        }
        return true
    }

    private fun hasExtension(value: String): Boolean{
        if(FilenameUtils.getExtension(value).isNotEmpty()){
            return true
        }
        return false
    }
}

