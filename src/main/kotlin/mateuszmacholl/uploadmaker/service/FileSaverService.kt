package mateuszmacholl.uploadmaker.service

import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import mateuszmacholl.uploadmaker.config.exception.file.FileStorageException
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.io.ByteArrayInputStream
import java.io.File
import java.net.URLConnection
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FileSaverService(fileStorageProperties: FileStorageProperties) {
    private var fileStorageLocation: Path = Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize()

    init {
        try {
            Files.createDirectories(this.fileStorageLocation)
        } catch (ex: Exception) {
            throw FileStorageException("Could not create the directory where the uploaded files will be stored", ex)
        }
    }

    fun save(file: ByteArray, name: String) {
        val cleanName = StringUtils.cleanPath(name)

        if (cleanName.contains("..")) {
            throw FileStorageException("Filename contains invalid path sequence $cleanName")
        }

        val targetLocation = this.fileStorageLocation.resolve(cleanName)

        val mimeType = URLConnection.guessContentTypeFromStream(ByteArrayInputStream(file))

        val index = mimeType.lastIndexOf('/')
        val type = mimeType.substring(index + 1, mimeType.length)

        FileUtils.writeByteArrayToFile(File("${targetLocation.toAbsolutePath()}.$type"), file)
    }
}