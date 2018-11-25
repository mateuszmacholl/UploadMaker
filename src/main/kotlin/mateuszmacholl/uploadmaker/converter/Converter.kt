package mateuszmacholl.uploadmaker.converter

interface Converter<in from, out to>{
    fun convert(from: from): to
    fun convert(from: List<from>): List<to>{
        return from.map { this.convert(it) }
    }
}