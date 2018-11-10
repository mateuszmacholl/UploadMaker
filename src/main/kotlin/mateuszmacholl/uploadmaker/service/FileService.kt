package mateuszmacholl.uploadmaker.service

import mateuszmacholl.uploadmaker.model.FileEntity
import mateuszmacholl.uploadmaker.repository.FileRepo
import mateuszmacholl.uploadmaker.service.async.AsyncFilePrepareService
import mateuszmacholl.uploadmaker.specification.FileEntitySpec
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class FileService(private val asyncFilePrepareService: AsyncFilePrepareService,
                  private val fileRepo: FileRepo) {


    fun getAll(fileEntitySpec: FileEntitySpec, pageable: Pageable): MutableIterable<FileEntity> {
        return fileRepo.findAll(fileEntitySpec, pageable)
    }

    fun save(filesEntities: List<FileEntity>): List<FileEntity>{
        val updatedFilesEntities = filesEntities.map { this.asyncFilePrepareService.prepare(it) }.map { it.get() }
        return updatedFilesEntities.map { this.fileRepo.save(it) }
    }
}