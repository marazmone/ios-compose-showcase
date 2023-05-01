package domain.mapper.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface Mapper<SOURCE, RESULT> {

    fun map(source: SOURCE): RESULT

    fun list(source: List<SOURCE>): List<RESULT> = source.map { map(it) }

    suspend fun mapAsync(source: SOURCE): RESULT =
        withContext(Dispatchers.Default) { map(source) }

    suspend fun listAsync(source: List<SOURCE>): List<RESULT> =
        withContext(Dispatchers.Default) { list(source) }
}