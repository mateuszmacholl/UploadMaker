package mateuszmacholl.uploadmaker.converter

import mateuszmacholl.uploadmaker.utils.FactoryContext
import org.springframework.stereotype.Service

@Service //factory class
class ConverterContext: FactoryContext<Converter<*, *>>()