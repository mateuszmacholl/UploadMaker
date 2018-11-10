package mateuszmacholl.uploadmaker.service

import mateuszmacholl.uploadmaker.model.FileEntity
import mateuszmacholl.uploadmaker.service.nameConstructor.FileNameConstructorService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import java.util.concurrent.Future

@Service
class FileAsyncQueueService(private val fileDownloaderService: FileDownloaderService,
                            private val fileSaverService: FileSaverService,
                            private val fileNameConstructorService: FileNameConstructorService) {

    @Async
    fun start(fileEntity: FileEntity): Future<FileEntity> {
        val file = this.fileDownloaderService.download(fileEntity.url!!)
        fileEntity.name = fileNameConstructorService.construct(file, fileEntity.url!!, fileEntity.name!!)
        fileEntity.path = fileSaverService.save(file, fileEntity.name!!)
        return AsyncResult<FileEntity>(fileEntity)
    }

    /*
    fun start(filesEntities: List<FileEntity>): List<Future<FileEntity>> {
        return filesEntities.map { this.start(it) }
    }
    */
}