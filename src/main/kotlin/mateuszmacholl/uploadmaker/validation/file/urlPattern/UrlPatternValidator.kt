package mateuszmacholl.uploadmaker.validation.file.urlPattern

import org.apache.commons.validator.routines.UrlValidator
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class UrlPatternValidator : ConstraintValidator<UrlPattern, String> {

    override fun initialize(constraint: UrlPattern) {}

    override fun isValid(url: String, context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()
        if (!UrlValidator().isValid(url)) {
            context.buildConstraintViolationWithTemplate("wrong url pattern")
                    .addConstraintViolation()
            return false
        }
        return true
    }

    private fun validUrl(url: String): Boolean {
        return try {
            URL(url).toURI()
            true
        } catch (exception: URISyntaxException) {
            false
        } catch (exception: MalformedURLException) {
            false
        }

    }
}

