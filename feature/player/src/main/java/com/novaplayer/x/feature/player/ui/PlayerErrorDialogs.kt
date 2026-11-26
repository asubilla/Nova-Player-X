package com.novaplayer.x.feature.player.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.media3.common.PlaybackException
import com.novaplayer.x.core.ui.R as coreUiR
import com.novaplayer.x.feature.player.model.DecoderRecoveryState
import com.novaplayer.x.feature.player.model.DecoderRecoveryStatus
import com.novaplayer.x.feature.player.model.DecoderTrackType
import com.novaplayer.x.feature.player.model.labelRes

@Composable
internal fun PlayerErrorDialogs(
    decoderRecoveryState: DecoderRecoveryState,
    playbackError: PlaybackException?,
    hasNextMediaItem: Boolean,
    onTryDecoderFallback: () -> Unit,
    onPlayNextVideo: () -> Unit,
    onExit: () -> Unit,
) {
    val unsupportedMode = decoderRecoveryState.unsupportedMode
    val recoveryTrackType = decoderRecoveryState.trackType
    if (
        decoderRecoveryState.status == DecoderRecoveryStatus.AWAITING_CONFIRMATION &&
        unsupportedMode != null &&
        recoveryTrackType != null
    ) {
        AlertDialog(
            onDismissRequest = {
                if (recoveryTrackType == DecoderTrackType.VIDEO) onTryDecoderFallback()
            },
            title = { Text(text = stringResource(coreUiR.string.decoder)) },
            text = {
                Text(
                    text = stringResource(
                        id = when (recoveryTrackType) {
                            DecoderTrackType.VIDEO ->
                                coreUiR.string.video_decoder_mode_not_supported

                            DecoderTrackType.AUDIO ->
                                coreUiR.string.audio_decoder_mode_not_supported
                        },
                        stringResource(unsupportedMode.labelRes),
                    ),
                )
            },
            confirmButton = {
                TextButton(onClick = onTryDecoderFallback) {
                    Text(text = stringResource(coreUiR.string.okay))
                }
            },
        )
    }

    val allDecoderModesFailed = decoderRecoveryState.status == DecoderRecoveryStatus.FAILED
    val showPlayerError = allDecoderModesFailed ||
        (
            decoderRecoveryState.status == DecoderRecoveryStatus.NONE &&
                playbackError != null
            )
    if (showPlayerError) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = stringResource(coreUiR.string.error_playing_video)) },
            text = {
                Text(
                    text = playbackError?.message
                        ?: stringResource(
                            id = if (allDecoderModesFailed) {
                                coreUiR.string.no_supported_decoder
                            } else {
                                coreUiR.string.unknown_error
                            },
                        ),
                )
            },
            confirmButton = {
                if (hasNextMediaItem) {
                    TextButton(onClick = onPlayNextVideo) {
                        Text(text = stringResource(coreUiR.string.play_next_video))
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = onExit) { Text(text = stringResource(coreUiR.string.exit)) }
            },
        )
    }
}
