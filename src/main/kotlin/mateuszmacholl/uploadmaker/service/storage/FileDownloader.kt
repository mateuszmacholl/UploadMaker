package mateuszmacholl.uploadmaker.service.storage

import mateuszmacholl.uploadmaker.exception.file.WrongUrlException
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL


@Component
class FileDownloader {

    fun downloadFromUrl(url: String): ByteArray {
        try {
            val conn = URL(url).openConnection()
            conn.connectTimeout = 5000
            conn.readTimeout = 5000
            conn.connect()

            val stream = ByteArrayOutputStream()
            IOUtils.copy(conn.getInputStream(), stream)

            return stream.toByteArray()
        } catch (ex: IOException) {
            throw WrongUrlException("File not found, wrong url: $url", ex)
        }
    }
}