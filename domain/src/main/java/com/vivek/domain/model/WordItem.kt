package com.vivek.domain.model

data class WordItem(
    val license: License,
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val phonetic: String?=null,
    val sourceUrls: List<String>,
    val word: String
)