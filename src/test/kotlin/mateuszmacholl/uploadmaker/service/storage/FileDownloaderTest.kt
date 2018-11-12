package mateuszmacholl.uploadmaker.service.storage

import mateuszmacholl.uploadmaker.config.exception.file.WrongUrlException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class FileDownloaderTest {
    private val fileDownloader = FileDownloader()
    private lateinit var url: String

    @Test
    fun download(){
        //given
        url = "https://google.com"
        //when
        val file = fileDownloader.downloadFromUrl(url)
        //then
        assertNotNull(file)
    }

    @Test
    fun download_throwWrongUrlException(){
        //given
        url = "wrong-Url-Pattern"
        //then
        Assertions.assertThrows<WrongUrlException>(WrongUrlException::class.java) {
            //when
           fileDownloader.downloadFromUrl(url)
        }
    }
}