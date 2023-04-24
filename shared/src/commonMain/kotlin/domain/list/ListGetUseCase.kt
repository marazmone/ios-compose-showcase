package domain.list

import kotlinx.coroutines.delay

class ListGetUseCase {

    suspend fun execute(): List<String> {
        delay(2000)
        return List(100) { "Row #$it" }
    }
}