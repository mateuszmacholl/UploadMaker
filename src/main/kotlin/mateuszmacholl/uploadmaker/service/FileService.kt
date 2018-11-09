package mateuszmacholl.uploadmaker.service

import mateuszmacholl.uploadmaker.dto.FileDto
import org.springframework.stereotype.Service


@Service
class FileService(private val fileDownloaderService: FileDownloaderService,
                  private val fileSaverService: FileSaverService) {

    fun save(fileDto: FileDto) {
        val file = this.fileDownloaderService.download(fileDto.url)
        if (file != null) {
            fileSaverService.save(file, fileDto.name)
        }
    }
}