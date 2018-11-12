package mateuszmacholl.uploadmaker.controller

import mateuszmacholl.uploadmaker.converter.ConverterContext
import mateuszmacholl.uploadmaker.converter.FileEntityConverter
import mateuszmacholl.uploadmaker.converter.ShowFileDtoConverter
import mateuszmacholl.uploadmaker.request.FilesDtoRequest
import mateuszmacholl.uploadmaker.service.FileService
import mateuszmacholl.uploadmaker.specification.FileEntitySpec
import mateuszmacholl.uploadmaker.validation.file.fileExistByName.FileExistByName
import org.springframework.core.io.Resource
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.servlet.http.HttpServletRequest


@RestController
@Validated
@RequestMapping(value = ["/files"])
class FileController(private val fileService: FileService,
                     private val converterContext: ConverterContext) {


    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getAll(fileEntitySpec: FileEntitySpec, pageable: Pageable): ResponseEntity<*> {
        val filesEntities = fileService.getAll(fileEntitySpec, pageable)
        val showFilesDto = filesEntities.map { converterContext.get(ShowFileDtoConverter::class.java).convert(it)}
        return ResponseEntity(showFilesDto, HttpStatus.OK)
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun add(@RequestBody @Validated filesDtoRequest: FilesDtoRequest): ResponseEntity<*> {
        val files = filesDtoRequest.files
        val filesEntities = converterContext.get(FileEntityConverter::class.java).convert(files)
        val createdFiles = filesEntities.map { fileService.save(it) }
                .map {
                    try {
                        it.get()
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
        return ResponseEntity(createdFiles, HttpStatus.OK)
    }

    @RequestMapping(value = ["/download"], method = [RequestMethod.GET])
    fun download(@RequestParam @FileExistByName name: String, request: HttpServletRequest): ResponseEntity<Resource> {
        val file = this.fileService.findByName(name)
        val resource = this.fileService.download(file!!)
        val contentType = getContentType(resource, request)
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${resource.filename}")
                .body(resource)
    }

    private fun getContentType(resource: Resource, request: HttpServletRequest): String {
        var contentType: String? = null
        try {
            contentType = request.servletContext.getMimeType(resource.file.absolutePath)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        if (contentType == null) {
            contentType = "application/octet-stream"
        }
        return contentType
    }
}