package mateuszmacholl.uploadmaker.service.storage

import mateuszmacholl.uploadmaker.config.exception.file.FileNotFoundException
import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FileLoaderTest {
    private val fileStorageProperties = FileStorageProperties()
    private lateinit var fileLoader: FileLoader
    private val fileName = "test/google.html"

    @BeforeEach
    fun init(){
        //given
        fileStorageProperties.uploadDir = "uploads"
        fileLoader = FileLoader(fileStorageProperties)
    }

    @Test
    fun loadAsResource(){
        //when
        val resource = fileLoader.loadFromDisk(fileName)
        //then
        assertNotNull(resource)
    }

    @Test
    fun loadAsResource_throwFileNotFoundException(){
        //given
        val wrongFileName = "wrongFileName.aaa"
        //then
        Assertions.assertThrows<FileNotFoundException>(FileNotFoundException::class.java) {
            //when
            fileLoader.loadFromDisk(wrongFileName)
        }
    }
}