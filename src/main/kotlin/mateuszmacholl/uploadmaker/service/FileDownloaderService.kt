package mateuszmacholl.uploadmaker.service

import mateuszmacholl.uploadmaker.config.exception.file.FileNotFoundException
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL


@Service
class FileDownloaderService {

    fun download(url: String): ByteArray {
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

    fun download(url: List<String>): List<ByteArray>{
        return url.map { download(it) }
    }
}