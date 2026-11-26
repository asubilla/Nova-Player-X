package com.novaplayer.x.feature.videopicker.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.novaplayer.x.core.model.MediaViewMode
import com.novaplayer.x.core.ui.R

@Composable
fun MediaViewMode.name(): String = when (this) {
    MediaViewMode.VIDEOS -> stringResource(id = R.string.videos)
    MediaViewMode.FOLDERS -> stringResource(id = R.string.folders)
    MediaViewMode.FOLDER_TREE -> stringResource(id = R.string.tree)
}
