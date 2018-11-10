package mateuszmacholl.uploadmaker.service.fileNameConstructor

import org.springframework.stereotype.Service

@Service
class FileNameConstructorService(private val fileExtensionService: FileExtensionService,
                                 private val fileMimeTypeService: FileMimeTypeService) {
    fun construct(file: ByteArray, url: String, name: String): String{
        var extension = fileMimeTypeService.getMimeType(file)
        if(extension  == ""){
            extension = fileExtensionService.getExtension(url)
        }
        return StringBuffer().append(name).append(".").append(extension).toString()
    }
}