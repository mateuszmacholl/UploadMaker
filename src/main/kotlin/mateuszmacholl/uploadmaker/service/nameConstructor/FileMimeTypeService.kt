package mateuszmacholl.uploadmaker.service.nameConstructor

import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.net.URLConnection

@Service
class FileMimeTypeService {
    fun getMimeType(file: ByteArray): String {
        val mimeType = URLConnection.guessContentTypeFromStream(ByteArrayInputStream(file)) ?: return ""
        val index = mimeType.lastIndexOf('/')
        return mimeType.substring(index + 1, mimeType.length)
    }
}