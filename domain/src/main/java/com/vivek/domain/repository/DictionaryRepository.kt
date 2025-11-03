package com.vivek.domain.repository

import com.vivek.core.util.Result
import com.vivek.domain.model.WordItem
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun getWordResult(word: String): Flow<Result<WordItem>>
}