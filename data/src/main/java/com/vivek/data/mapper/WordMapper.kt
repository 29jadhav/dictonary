package com.vivek.data.mapper

import android.util.Log
import com.vivek.data.dto.DefinitionDto
import com.vivek.data.dto.LicenseDto
import com.vivek.data.dto.MeaningDto
import com.vivek.data.dto.PhoneticDto
import com.vivek.data.dto.WordItemDto
import com.vivek.domain.model.Definition
import com.vivek.domain.model.License
import com.vivek.domain.model.Meaning
import com.vivek.domain.model.Phonetic
import com.vivek.domain.model.WordItem


fun WordItemDto.toWordItem() = WordItem(
    word = word,
    meanings = meanings.map {
        Log.d("TAG","meanings = ${meanings.toString()}")
        it.toMeaning()
    },
    phonetic = phonetic,
    license = licenseDtoToLicense(license),
    phonetics = phonetics.map {
        it.toPhonetic()
    },
    sourceUrls = sourceUrls
)

fun MeaningDto.toMeaning() = Meaning(
    definition = definitionDtoToDefinition(definitions[0]),
    partOfSpeech = partOfSpeech,
    antonyms = antonyms,
    synonyms = synonyms
)

fun definitionDtoToDefinition(definitionDto: DefinitionDto) = Definition(
    definition = definitionDto.definition,
    example = definitionDto.example,
    antonyms = definitionDto.antonyms,
    synonyms = definitionDto.synonyms
)

fun licenseDtoToLicense(license: LicenseDto?) = License(
    name = license?.name,
    url = license?.url
)

fun PhoneticDto.toPhonetic() = Phonetic(
    audio = audio,
    license = licenseDtoToLicense(license),
    sourceUrl = sourceUrl,
    text = text
)




