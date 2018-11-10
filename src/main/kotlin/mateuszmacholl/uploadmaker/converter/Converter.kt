package mateuszmacholl.uploadmaker.converter

interface Converter<in from, out to>{
    fun convert(from: from): to
}