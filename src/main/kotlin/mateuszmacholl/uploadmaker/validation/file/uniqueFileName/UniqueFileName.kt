package mateuszmacholl.uploadmaker.validation.file.uniqueFileName

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueFileNameValidator::class])
annotation class UniqueFileName(
        val message: String = "",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)