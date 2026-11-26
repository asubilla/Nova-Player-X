package com.novaplayer.x.settings.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.novaplayer.x.core.model.ControlButtonsPosition
import com.novaplayer.x.core.ui.R

@Composable
fun ControlButtonsPosition.name(): String {
    val stringRes = when (this) {
        ControlButtonsPosition.LEFT -> R.string.control_buttons_alignment_left
        ControlButtonsPosition.RIGHT -> R.string.control_buttons_alignment_right
    }

    return stringResource(id = stringRes)
}
