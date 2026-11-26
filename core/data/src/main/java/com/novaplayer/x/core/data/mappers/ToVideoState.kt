package com.novaplayer.x.core.data.mappers

import com.novaplayer.x.core.data.models.VideoState
import com.novaplayer.x.core.database.converter.UriListConverter
import com.novaplayer.x.core.database.entities.MediumStateEntity

fun MediumStateEntity.toVideoState(): VideoState = VideoState(
    path = uriString,
    position = playbackPosition.takeIf { it != 0L },
    audioTrackIndex = audioTrackIndex,
    subtitleTrackIndex = subtitleTrackIndex,
    externalSubs = UriListConverter.fromStringToList(urlString = externalSubs),
    externalAudio = UriListConverter.fromStringToList(urlString = externalAudio),
    videoScale = videoScale,
    subtitleDelayMilliseconds = subtitleDelayMilliseconds,
    subtitleSpeed = subtitleSpeed,
)
