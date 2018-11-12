package mateuszmacholl.uploadmaker.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class FileEntity (
        var name: String,
        var url: String
){
    @Id
    @GeneratedValue
    var id: Int? = null
}