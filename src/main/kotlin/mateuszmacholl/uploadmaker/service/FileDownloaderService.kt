package mateuszmacholl.uploadmaker.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*


@Service
class FileDownloaderService(private val restTemplate: RestTemplate) {

    fun download(url: String): ByteArray? {
        try {
            val headers = HttpHeaders()
            headers.accept = Arrays.asList(MediaType.APPLICATION_OCTET_STREAM)
            val entity = HttpEntity<Any>(headers)
            val response = this.restTemplate
                    .exchange(url, HttpMethod.GET, entity, ByteArray::class.java)
            return response.body
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
        return null
    }

}