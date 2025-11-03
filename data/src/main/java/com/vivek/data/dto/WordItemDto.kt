package com.vivek.data.dto

data class WordItemDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String?=null,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
)