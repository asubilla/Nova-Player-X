package com.novaplayer.x.feature.videopicker.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.novaplayer.x.core.model.MediaLayoutMode
import com.novaplayer.x.core.ui.R

@Composable
fun MediaLayoutMode.name(): String = when (this) {
    MediaLayoutMode.LIST -> stringResource(id = R.string.list)
    MediaLayoutMode.GRID -> stringResource(id = R.string.grid)
}
