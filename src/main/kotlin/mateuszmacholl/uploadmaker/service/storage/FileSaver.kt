package mateuszmacholl.uploadmaker.service.storage

import mateuszmacholl.uploadmaker.exception.file.FileStorageException
import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.io.File
import java.io.IOException
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

    fun saveOnDisk(file: ByteArray, fileName: String) {
        val targetLocation = prepareLocation(fileName)
        try {
            FileUtils.writeByteArrayToFile(File(targetLocation), file)
        } catch (ex: IOException){
            throw IllegalArgumentException("file name can't be empty")
        }
    }

    private fun prepareLocation(fileName: String): String {
        val cleanName = StringUtils.cleanPath(fileName)
        return this.fileStorageLocation.resolve(cleanName).toString()
    }

}