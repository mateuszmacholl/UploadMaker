package mateuszmacholl.uploadmaker.specification

import mateuszmacholl.uploadmaker.model.FileEntity
import net.kaczmarzyk.spring.data.jpa.domain.Like
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.data.jpa.domain.Specification


@And(
        Spec(path = "name", spec = Like::class),
        Spec(path = "url", spec = Like::class)
)
interface FileEntitySpec: Specification<FileEntity>