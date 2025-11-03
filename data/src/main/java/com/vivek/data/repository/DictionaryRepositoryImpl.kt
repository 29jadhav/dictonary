package com.vivek.data.repository

import com.google.gson.Gson // Import Gson
import com.vivek.core.util.Result
import com.vivek.data.api.DictionaryApi
import com.vivek.data.mapper.toWordItem
import com.vivek.domain.model.WordItem
import com.vivek.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(private val dictionaryApi: DictionaryApi) :
    DictionaryRepository {
    override suspend fun getWordResult(word: String): Flow<Result<WordItem>> {
        return flow {
            emit(Result.Loading(true))
            val remoteResult = try {
                dictionaryApi.getWords(word)
            } catch (e: Exception) {
                // Handle network exceptions (e.g., no internet, DNS error)
                emit(Result.Error("Couldn't reach server. Check your internet connection."))
                emit(Result.Loading(false))
                return@flow
            }

            if (remoteResult.isSuccessful) {
                // Use firstOrNull() to safely get the first item, avoiding crashes on empty lists
                remoteResult.body()?.firstOrNull()?.let { wordItemDto ->
                    emit(Result.Success(wordItemDto.toWordItem()))
                } ?: emit(Result.Error("Received a successful but empty response.")) // Handle case where list is empty
            } else {
                // This is the key part: converting the errorBody
                val errorBodyString = remoteResult.errorBody()?.string()
                val errorMessage = try {
                    // Use Gson to convert the JSON string to your ErrorResponse object
                    val errorResponse = Gson().fromJson(errorBodyString, ErrorResponse::class.java)
                    // Use the helpful message from the API
                    errorResponse?.message ?: "An unknown error occurred."
                } catch (e: Exception) {
                    // Fallback if parsing fails or errorBody is malformed
                    remoteResult.message() ?: "An unknown error occurred."
                }
                emit(Result.Error(errorMessage))
            }
            emit(Result.Loading(false)) // Ensure loading is turned off at the end
        }
    }
}
