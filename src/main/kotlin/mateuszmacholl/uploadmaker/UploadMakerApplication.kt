package mateuszmacholl.uploadmaker

import mateuszmacholl.uploadmaker.config.properties.FileStorageProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableConfigurationProperties(
    FileStorageProperties::class
)
@EnableAsync
class UploadMakerApplication

fun main(args: Array<String>) {
    runApplication<UploadMakerApplication>(*args)
}
