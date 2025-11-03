package com.vivek.dictionaryapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivek.dictionaryapp.R

@Composable
fun SearchTopBar(
    modifier: Modifier,
    searchWord: String,
    onSearchWordChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        value = searchWord,
        onValueChange = onSearchWordChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClick() }
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(R.string.search_a_word),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onSearchClick() }
            )
        },
        label = {
            Text(
                text = stringResource(R.string.search_a_word),
                fontSize = 15.sp,
                modifier = Modifier.alpha(0.7f)
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 19.5.sp,
        )
    )
}