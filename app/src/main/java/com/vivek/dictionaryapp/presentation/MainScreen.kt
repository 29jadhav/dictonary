package com.vivek.dictionaryapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vivek.dictionaryapp.R
import com.vivek.domain.model.WordItem

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    BarColor()
    val mainViewModel = hiltViewModel<MainViewModel>()
    val mainState by mainViewModel.mainState.collectAsState()
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            SearchTopBar(
                modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues()),
                searchWord = mainState.searchWord,
                onSearchWordChange = { mainViewModel.onEvent(MainUIEvent.OnSearchWordChange(it)) },
                onSearchClick = { mainViewModel.onEvent(MainUIEvent.OnSearchClick) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainScreenUI(mainState, mainViewModel)
        }

    }
}

@Composable
fun BarColor() {
    val systemUIColor = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.background
    LaunchedEffect(color) {
        systemUIColor.setSystemBarsColor(color)
    }

}

@Composable
private fun MainScreenUI(mainState: MainState, mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(horizontal = 10.dp)
    ) {
        mainState.wordItem?.let { wordItem ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = wordItem.word,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                    contentDescription = stringResource(R.string.play_pronunciation),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { mainViewModel.onEvent(MainUIEvent.OnSpeakerClick) }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            wordItem.phonetic?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp, topEnd =
                            10.dp
                    )
                )
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(0.7f))
        ) {
            if (mainState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(80.dp)
                        .align(
                            Alignment
                                .Center
                        ), color = MaterialTheme.colorScheme.primary
                )
            }
            mainState.errorMessage?.let {
                if (it.isNotEmpty()) {
                    Text(text = it)
                }
            }

            mainState.wordItem?.let { wordItem ->
                WordDetails(wordItem)
            }
        }
    }
}


@Composable
fun WordDetails(wordItem: WordItem) {
    LazyColumn(contentPadding = PaddingValues(vertical = 32.dp)) {
        items(wordItem.meanings.size) { index ->
            DefinitionsItem(wordItem.meanings[index], index)
        }
    }
}
