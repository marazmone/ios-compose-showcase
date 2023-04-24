package domain.detail

import kotlinx.coroutines.delay

class DetailGetUseCase {

    suspend fun execute(id: String): String {
        delay(2000)
        return "Detail #$id"
    }
}