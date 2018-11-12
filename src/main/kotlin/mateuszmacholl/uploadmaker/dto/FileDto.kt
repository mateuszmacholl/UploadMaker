package mateuszmacholl.uploadmaker.dto

import mateuszmacholl.uploadmaker.validation.file.hasExtension.HasExtension
import mateuszmacholl.uploadmaker.validation.file.uniqueFileName.UniqueFileName
import mateuszmacholl.uploadmaker.validation.file.urlPattern.UrlPattern
import mateuszmacholl.uploadmaker.validation.utils.filled.Filled

data class FileDto(
        @field:Filled
        @field:UrlPattern
        val url: String,
        @field:Filled
        @field:HasExtension
        @field:UniqueFileName
        val name: String
)