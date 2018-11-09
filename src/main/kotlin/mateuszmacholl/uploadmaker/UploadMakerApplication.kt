package mateuszmacholl.uploadmaker

import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
    FileStorageProperties::class
)
class UploadMakerApplication

fun main(args: Array<String>) {
    runApplication<UploadMakerApplication>(*args)
}
