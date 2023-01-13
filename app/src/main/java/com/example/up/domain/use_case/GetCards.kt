package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.data.remote.dto.toCard
import com.example.up.domain.model.Card
import com.example.up.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCards @Inject constructor(
    private val repository: CardRepository
) {
    operator fun invoke(): Flow<Resource<List<Card>>> = flow {
        try {
            emit(Resource.Loading())
            val cards = repository.getCards().map { it.toCard() }
            emit(Resource.Success(cards))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}