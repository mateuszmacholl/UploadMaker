package mateuszmacholl.uploadmaker.service.storage

import mateuszmacholl.uploadmaker.exception.file.FileNotFoundException
import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import java.nio.file.Path
import java.nio.file.Paths


@Component
class FileLoader(fileStorageProperties: FileStorageProperties) {
    private val fileStorageLocation: Path = Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize()

    fun loadFromDisk(fileName: String): Resource {
        val filePath = this.fileStorageLocation.resolve(fileName).normalize()
        val resource = UrlResource(filePath.toUri())
        return if (resource.exists()) {
            resource
        } else {
            throw FileNotFoundException("File not found on disk $fileName")
        }
    }
}