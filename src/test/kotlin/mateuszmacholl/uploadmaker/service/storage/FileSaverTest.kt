package mateuszmacholl.uploadmaker.service.storage

import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class FileSaverTest {
    private val fileStorageProperties = FileStorageProperties()
    private lateinit var fileSaver: FileSaver
    private val fileDownloader = FileDownloader()
    private val file = fileDownloader.downloadFromUrl("https://google.com")
    private val fileName = "google"
    private lateinit var pathToFile :String

    @BeforeEach
    fun init(){
        //given
        fileStorageProperties.uploadDir = "uploads"
        fileSaver = FileSaver(fileStorageProperties)
        pathToFile =  Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize().resolve(fileName).toString()
    }

    @Test
    fun saveOnDisk(){
        //when
        fileSaver.saveOnDisk(file, fileName)
        //then
        val path = Paths.get(pathToFile)
        assertTrue(Files.exists(path))
    }

    @AfterEach
    fun afterEach(){
        val file = File(pathToFile)
        file.delete()
    }
}