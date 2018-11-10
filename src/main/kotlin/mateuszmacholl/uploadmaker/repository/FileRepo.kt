package mateuszmacholl.uploadmaker.repository

import mateuszmacholl.uploadmaker.model.FileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface FileRepo : JpaRepository<FileEntity, Int>, JpaSpecificationExecutor<FileEntity> {
}