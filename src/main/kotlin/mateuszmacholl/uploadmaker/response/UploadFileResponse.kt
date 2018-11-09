package mateuszmacholl.uploadmaker.response

data class UploadFileResponse (
    private val fileName: String,
    private val fileDownloadUri: String,
    private val fileType: String,
    private val size: Long
)