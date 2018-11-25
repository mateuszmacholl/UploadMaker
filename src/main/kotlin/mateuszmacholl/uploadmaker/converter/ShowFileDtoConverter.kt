package mateuszmacholl.uploadmaker.converter

import mateuszmacholl.uploadmaker.dto.ShowFileDto
import mateuszmacholl.uploadmaker.model.FileEntity
import org.springframework.stereotype.Service

@Service
class ShowFileDtoConverter: Converter<FileEntity, ShowFileDto> {
    override fun convert(from: FileEntity): ShowFileDto {
        return ShowFileDto(from.name, from.url)
    }
}