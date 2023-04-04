package com.example.webviewapp.ui.mini_game_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.webviewapp.R

@Composable
fun MenuGameScreen(navigateToGame: () -> Unit, navigateToLeaderboard: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { navigateToGame() }) {
                Text(text = stringResource(R.string.play), style = MaterialTheme.typography.bodyLarge)
            }
            Button(onClick = { navigateToLeaderboard() }) {
                Text(text = stringResource(id = R.string.leaderboard), style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.rules), color = Color.Gray)
        }
    }
}