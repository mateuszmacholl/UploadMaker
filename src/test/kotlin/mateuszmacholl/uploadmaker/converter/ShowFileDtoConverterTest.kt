package mateuszmacholl.uploadmaker.converter

import mateuszmacholl.uploadmaker.model.FileEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ShowFileDtoConverterTest {
    private val showFileDtoConverter = ShowFileDtoConverter()
    private val from = FileEntity("url", "name")
    private val from2 = FileEntity("url2", "name2")

    @Test
    fun convert(){
        //when
        val fileEntity = showFileDtoConverter.convert(from)
        //then
        assertEquals(from.name, fileEntity.name)
        assertEquals(from.url, fileEntity.url)
    }

    @Test
    fun convertList(){
        //given
        val list = mutableListOf(from, from2)
        //when
        val fileEntity = showFileDtoConverter.convert(list)
        //then
        assertEquals(from2.name, fileEntity[1].name)
        assertEquals(from2.url, fileEntity[1].url)
    }
}