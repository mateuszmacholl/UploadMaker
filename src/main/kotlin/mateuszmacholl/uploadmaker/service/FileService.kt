package mateuszmacholl.uploadmaker.service

import mateuszmacholl.uploadmaker.model.FileEntity
import mateuszmacholl.uploadmaker.repository.FileRepo
import mateuszmacholl.uploadmaker.service.storage.FileDownloader
import mateuszmacholl.uploadmaker.service.storage.FileLoader
import mateuszmacholl.uploadmaker.service.storage.FileSaver
import mateuszmacholl.uploadmaker.specification.FileEntitySpec
import org.springframework.core.io.Resource
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import java.util.concurrent.Future


@Service
class FileService(private val fileDownloader: FileDownloader,
                  private val fileSaver: FileSaver,
                  private val fileLoader: FileLoader,
                  private val fileRepo: FileRepo) {


    fun getAll(fileEntitySpec: FileEntitySpec, pageable: Pageable): MutableIterable<FileEntity> {
        return fileRepo.findAll(fileEntitySpec, pageable)
    }

    @Async
    fun save(fileEntity: FileEntity): Future<FileEntity> {
        val file = fileDownloader.downloadFromUrl(fileEntity.url)
        fileSaver.saveOnDisk(file, fileEntity.name)
        val savedFile = this.fileRepo.save(fileEntity)
        return AsyncResult(savedFile)
    }

    fun download(fileEntity: FileEntity): Resource {
        return fileLoader.loadFromDisk(fileEntity.name)
    }

    fun findByName(name: String): FileEntity? {
        return this.fileRepo.findByName(name)
    }
}