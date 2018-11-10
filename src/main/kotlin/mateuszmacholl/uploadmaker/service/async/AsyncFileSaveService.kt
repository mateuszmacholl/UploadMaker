package mateuszmacholl.uploadmaker.service.async

import mateuszmacholl.uploadmaker.model.FileEntity
import mateuszmacholl.uploadmaker.service.FileDownloaderService
import mateuszmacholl.uploadmaker.service.FileSaverService
import mateuszmacholl.uploadmaker.service.fileNameConstructor.FileNameConstructorService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import java.util.concurrent.Future

@Service
class AsyncFileSaveService(private val fileDownloaderService: FileDownloaderService,
                           private val fileSaverService: FileSaverService,
                           private val fileNameConstructorService: FileNameConstructorService) {

    @Async
    fun start(fileEntity: FileEntity): Future<FileEntity> {
        val file = this.fileDownloaderService.download(fileEntity.url!!)
        fileEntity.name = fileNameConstructorService.construct(file, fileEntity.url!!, fileEntity.name!!)
        fileEntity.path = fileSaverService.save(file, fileEntity.name!!)
        return AsyncResult<FileEntity>(fileEntity)
    }
}