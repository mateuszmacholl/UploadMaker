package mateuszmacholl.uploadmaker.converter

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterContextTest {

    @Autowired
    private lateinit var converterContext:ConverterContext

    @Test
    fun getConverter_returnCorrectConverter() {
        //when
        val converter = converterContext.get(ShowFileDtoConverter::class.java)
        //then
        assertTrue(ShowFileDtoConverter::class.isInstance(converter))
    }

    @Test
    fun getConverter_withNull_throwIllegalArgumentException() {
        //given
        val emptyConverterContext = ConverterContext()
        //then
        assertThrows<IllegalArgumentException>(IllegalArgumentException::class.java) {
            //when
            emptyConverterContext.get(ShowFileDtoConverter::class.java)
        }
    }


}