package com.vivek.data.api

import com.vivek.data.dto.WordDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("{word}")
    suspend fun getWords(@Path("word") word: String ): Response<WordDto?>

    companion object{
        const val  BASE_URL="https://api.dictionaryapi.dev/api/v2/entries/en/"
    }
}