package mateuszmacholl.uploadmaker.service.file.storage

import mateuszmacholl.uploadmaker.config.exception.file.FileStorageException
import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Component
class FileSaver(fileStorageProperties: FileStorageProperties) {
    private val fileStorageLocation: Path = Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize()

    init {
        try {
            Files.createDirectories(this.fileStorageLocation)
        } catch (ex: Exception) {
            throw FileStorageException("Could not create the directory where the uploaded files will be stored", ex)
        }
    }

    fun saveOnDisk(file: ByteArray, name: String): String {
        val targetLocation = prepareLocation(name)
        FileUtils.writeByteArrayToFile(File(targetLocation), file)
        return targetLocation
    }

    private fun prepareLocation(name: String): String {
        val cleanName = StringUtils.cleanPath(name)
        return this.fileStorageLocation.resolve(cleanName).toString()
    }

}