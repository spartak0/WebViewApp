package com.example.webviewapp.ui.no_internet_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.webviewapp.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoInternetScreen(viewModel: NoInternetViewModel = hiltViewModel(), navigateToMain:()->Unit) {
    val refreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.refresh() })
    val context = LocalContext.current.applicationContext

    LaunchedEffect(key1 = refreshing){
        val internetConnection = viewModel.checkInternet(context)
        if (internetConnection) navigateToMain()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState), contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.no_internet_connection),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.question_emoji),
                contentDescription = null,
                modifier = Modifier.padding(24.dp),
                contentScale = ContentScale.Crop
            )
        }
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
