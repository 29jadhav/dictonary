package com.vivek.dictionaryapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivek.dictionaryapp.R
import com.vivek.domain.model.Meaning

@Composable
fun DefinitionsItem(meaning: Meaning, index: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "${index + 1}. ${meaning.partOfSpeech} ",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                            Color.Transparent
                        )
                    )
                )
                .padding(top = 2.dp, bottom = 4.dp, start = 12.dp, end = 12.dp)
        )
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.definition),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = meaning.definition.definition,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        meaning.definition.example?.let {
            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = stringResource(R.string.example),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = meaning.definition.example ?: "",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}