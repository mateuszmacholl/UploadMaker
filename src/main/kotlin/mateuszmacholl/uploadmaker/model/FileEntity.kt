package mateuszmacholl.uploadmaker.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class FileEntity (
        var name: String? = null,
        var url: String? = null
){
    @Id
    @GeneratedValue
    var id: Int? = null
    @JsonIgnore
    var path: String? = null
}