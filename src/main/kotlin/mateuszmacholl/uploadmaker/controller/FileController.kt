package mateuszmacholl.uploadmaker.controller

import mateuszmacholl.uploadmaker.dto.FileDto
import mateuszmacholl.uploadmaker.service.FileService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping(value = ["/files"])
class FileController(private val fileService: FileService) {

    @RequestMapping(value = ["/{name}"], method = [RequestMethod.GET])
    fun getFile(){

    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun addFile(@RequestBody fileDto: FileDto ){
        fileService.save(fileDto)
    }
}