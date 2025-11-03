package com.vivek.data.dto

data class DefinitionDto(
    val antonyms: List<Any>,
    val definition: String,
    val example: String? = null,
    val synonyms: List<Any>
)