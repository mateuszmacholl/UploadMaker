package mateuszmacholl.uploadmaker.request

import mateuszmacholl.uploadmaker.dto.FileDto
import mateuszmacholl.uploadmaker.validation.file.uniqueFileNameInList.UniqueFileNameOnList
import javax.validation.Valid

data class FilesDtoRequest(
    @field:UniqueFileNameOnList
    @field:Valid
    val files: List<FileDto>
)