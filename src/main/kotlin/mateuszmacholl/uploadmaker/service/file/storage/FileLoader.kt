package mateuszmacholl.uploadmaker.service.file.storage

import mateuszmacholl.uploadmaker.config.exception.file.FileNotFoundException
import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.nio.file.Path
import java.nio.file.Paths


@Component
class FileLoader(fileStorageProperties: FileStorageProperties) {
    private val fileStorageLocation: Path = Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize()

    fun loadAsResource(fileName: String): Resource {
        try {
            val filePath = this.fileStorageLocation.resolve(fileName).normalize()
            val resource = UrlResource(filePath.toUri())
            return if (resource.exists()) {
                resource
            } else {
                throw FileNotFoundException("File not found on disk $fileName")
            }
        } catch (ex: MalformedURLException) {
            throw FileNotFoundException("File not found on disk $fileName", ex)
        }

    }
}