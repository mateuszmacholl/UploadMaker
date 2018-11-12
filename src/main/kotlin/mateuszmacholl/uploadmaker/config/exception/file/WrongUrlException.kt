package mateuszmacholl.uploadmaker.config.exception.file

class WrongUrlException: RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}