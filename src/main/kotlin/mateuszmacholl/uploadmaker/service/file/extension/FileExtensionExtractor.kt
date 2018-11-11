package mateuszmacholl.uploadmaker.service.file.extension

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.net.URL
import java.net.URLConnection

@Component
class FileExtensionExtractor {
    private fun fromUrl(url: String): String {
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

    private fun fromContentType(file: ByteArray): String {
        val mimeType = URLConnection.guessContentTypeFromStream(ByteArrayInputStream(file)) ?: return ""
        val index = mimeType.lastIndexOf('/')
        return mimeType.substring(index + 1, mimeType.length)
    }

    fun getExtension(file: ByteArray, url: String): String {
        var extension = this.fromContentType(file)
        if(extension  == ""){
            extension = this.fromUrl(url)
        }
        return extension
    }
}