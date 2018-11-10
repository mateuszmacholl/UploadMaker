package mateuszmacholl.uploadmaker.controller

import mateuszmacholl.uploadmaker.converter.FileConverter
import mateuszmacholl.uploadmaker.dto.FileDto
import mateuszmacholl.uploadmaker.service.FileService
import mateuszmacholl.uploadmaker.specification.FileEntitySpec
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping(value = ["/files"])
class FileController(private val fileService: FileService,
                     private val fileConverter: FileConverter) {


    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getAll(fileEntitySpec: FileEntitySpec, pageable: Pageable): ResponseEntity<*>{
        val filesEntities = fileService.getAll(fileEntitySpec, pageable)
        return ResponseEntity(filesEntities, HttpStatus.OK)
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun add(@RequestBody fileDtos: List<FileDto>): ResponseEntity<*>{
        val filesEntities = fileConverter.convert(fileDtos)
        val createdFiles = fileService.save(filesEntities)
        return ResponseEntity(createdFiles, HttpStatus.ACCEPTED)
    }
}