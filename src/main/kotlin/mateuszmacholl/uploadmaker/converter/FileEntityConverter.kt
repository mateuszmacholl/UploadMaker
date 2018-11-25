package mateuszmacholl.uploadmaker.converter

import mateuszmacholl.uploadmaker.dto.FileDto
import mateuszmacholl.uploadmaker.model.FileEntity
import org.springframework.stereotype.Service

@Service
class FileEntityConverter: Converter<FileDto, FileEntity> {
    override fun convert(from: FileDto): FileEntity {
        return FileEntity(from.name, from.url)
    }
}