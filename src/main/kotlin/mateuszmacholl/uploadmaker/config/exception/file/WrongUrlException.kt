package mateuszmacholl.uploadmaker.config.exception.file

class WrongUrlException: RuntimeException {
    constructor(message: String, cause: Throwable) : super(message, cause)
}