package mateuszmacholl.uploadmaker.service.file.storage

import mateuszmacholl.uploadmaker.config.exception.file.FileNotFoundException
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL


@Component
class FileDownloader {

    fun downloadByUrl(url: String): ByteArray {
        try {
            val conn = URL(url).openConnection()
            conn.connectTimeout = 5000
            conn.readTimeout = 5000
            conn.connect()

            val stream = ByteArrayOutputStream()
            IOUtils.copy(conn.getInputStream(), stream)

            return stream.toByteArray()
        } catch (ex: IOException) {
            throw FileNotFoundException("File not found, wrong url: $url", ex)
        }
    }
}