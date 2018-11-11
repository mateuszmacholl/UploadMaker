package mateuszmacholl.uploadmaker.config.exception.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


class FileNotFoundException : RuntimeException {
    constructor(message: String) : super(message) {}
    constructor(message: String, cause: Throwable) : super(message, cause) {}
}