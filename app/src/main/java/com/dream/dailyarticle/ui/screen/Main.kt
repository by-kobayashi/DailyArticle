package com.dream.dailyarticle.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Main(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Main")
    }
}

@Preview(name = "Main")
@Composable
private fun PreviewMain() {
    Main()
}