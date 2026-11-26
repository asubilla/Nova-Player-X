package com.novaplayer.x.core.domain

import com.novaplayer.x.core.common.di.DiQualifiers
import com.novaplayer.x.core.data.repository.MediaRepository
import com.novaplayer.x.core.data.repository.PreferencesRepository
import com.novaplayer.x.core.model.Sort
import com.novaplayer.x.core.model.Video
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
class GetSortedVideosUseCase(
    private val mediaRepository: MediaRepository,
    private val preferencesRepository: PreferencesRepository,
    @Named(DiQualifiers.DEFAULT_DISPATCHER) private val defaultDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(folderPath: String? = null): Flow<List<Video>> = combine(
        mediaRepository.observeVideos(folderPath = folderPath),
        preferencesRepository.applicationPreferences,
    ) { videoItems, preferences ->

        val nonExcludedVideos = videoItems.filterNot { it.parentPath in preferences.excludeFolders }

        val sort = Sort(
            by = preferences.sortBy,
            order = preferences.sortOrder,
        )
        nonExcludedVideos.sortedWith(sort.videoComparator())
    }.flowOn(context = defaultDispatcher)
}
