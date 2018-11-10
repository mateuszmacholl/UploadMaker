package mateuszmacholl.uploadmaker.service.nameConstructor

import org.springframework.stereotype.Service
import java.net.URL

@Service
class FileExtensionService {
    fun getExtension(url: String): String {
        val file = URL(url).file

        if (file.contains(".")) {
            val sub = file.substring(file.lastIndexOf('.') + 1)
            if (sub.isEmpty()) {
                return ""
            }
            return if (sub.contains("?")) {
                sub.substring(0, sub.indexOf('?'))
            } else sub
        }
        return ""
    }
}