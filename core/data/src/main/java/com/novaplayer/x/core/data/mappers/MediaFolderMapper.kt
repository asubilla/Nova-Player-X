package com.novaplayer.x.core.data.mappers

import com.novaplayer.x.core.media.services.MediaFolder
import com.novaplayer.x.core.model.Folder

internal fun MediaFolder.toFolder() = Folder(
    name = name,
    path = path,
    dateModified = dateModified,
    totalSize = totalSize,
    totalDuration = totalDuration,
    videosCount = videosCount,
    foldersCount = foldersCount,
)
