package com.vivek.domain.model

data class Meaning(
    val antonyms: List<String>,
    val definition: Definition,
    val partOfSpeech: String,
    val synonyms: List<String>
)