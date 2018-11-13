package mateuszmacholl.uploadmaker.exception.file

class FileStorageException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}