package mateuszmacholl.uploadmaker.converter

import mateuszmacholl.uploadmaker.dto.FileDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileEntityConverterTest {
    private val fileEntityConverter = FileEntityConverter()
    private val from = FileDto("url", "name")
    private val from2 = FileDto("url2", "name2")

    @Test
    fun convert(){
        //when
        val fileEntity = fileEntityConverter.convert(from)
        //then
        assertEquals(from.name, fileEntity.name)
        assertEquals(from.url, fileEntity.url)
    }

    @Test
    fun convertList(){
        //given
        val list = mutableListOf(from, from2)
        //when
        val fileEntity = fileEntityConverter.convert(list)
        //then
        assertEquals(from2.name, fileEntity[1].name)
        assertEquals(from2.url, fileEntity[1].url)
    }
}