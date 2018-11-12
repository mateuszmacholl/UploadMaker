package mateuszmacholl.uploadmaker.service.file.name

import org.springframework.stereotype.Component

@Component
class FileNameConstructor {
    fun construct(name: String, extension: String): String {
        return StringBuffer().append(name).append(".").append(extension).toString()
    }
}