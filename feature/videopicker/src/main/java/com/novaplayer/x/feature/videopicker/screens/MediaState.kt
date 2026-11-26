package com.novaplayer.x.feature.videopicker.screens

import com.novaplayer.x.core.model.Folder

sealed interface MediaState {
    data object Loading : MediaState
    data class Success(val data: Folder?) : MediaState
}
