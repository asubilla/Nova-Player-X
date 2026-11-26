package com.novaplayer.x.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.novaplayer.x.settings.Setting
import com.novaplayer.x.settings.navigation.aboutPreferencesEntry
import com.novaplayer.x.settings.navigation.appearancePreferencesEntry
import com.novaplayer.x.settings.navigation.audioPreferencesEntry
import com.novaplayer.x.settings.navigation.folderPreferencesEntry
import com.novaplayer.x.settings.navigation.generalPreferencesEntry
import com.novaplayer.x.settings.navigation.gesturePreferencesEntry
import com.novaplayer.x.settings.navigation.librariesEntry
import com.novaplayer.x.settings.navigation.mediaLibraryPreferencesEntry
import com.novaplayer.x.settings.navigation.navigateToAboutPreferences
import com.novaplayer.x.settings.navigation.navigateToAppearancePreferences
import com.novaplayer.x.settings.navigation.navigateToAudioPreferences
import com.novaplayer.x.settings.navigation.navigateToFolderPreferencesScreen
import com.novaplayer.x.settings.navigation.navigateToGeneralPreferences
import com.novaplayer.x.settings.navigation.navigateToGesturePreferences
import com.novaplayer.x.settings.navigation.navigateToLibraries
import com.novaplayer.x.settings.navigation.navigateToMediaLibraryPreferencesScreen
import com.novaplayer.x.settings.navigation.navigateToPlayerPreferences
import com.novaplayer.x.settings.navigation.navigateToSubtitlePreferences
import com.novaplayer.x.settings.navigation.navigateToThumbnailPreferencesScreen
import com.novaplayer.x.settings.navigation.playerPreferencesEntry
import com.novaplayer.x.settings.navigation.settingsEntry
import com.novaplayer.x.settings.navigation.subtitlePreferencesEntry
import com.novaplayer.x.settings.navigation.thumbnailPreferencesEntry

fun EntryProviderScope<NavKey>.settingsNavGraph(backStack: NavBackStack<NavKey>) {
    settingsEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onItemClick = { setting ->
            when (setting) {
                Setting.APPEARANCE -> backStack.navigateToAppearancePreferences()
                Setting.MEDIA_LIBRARY -> backStack.navigateToMediaLibraryPreferencesScreen()
                Setting.PLAYER -> backStack.navigateToPlayerPreferences()
                Setting.GESTURES -> backStack.navigateToGesturePreferences()
                Setting.AUDIO -> backStack.navigateToAudioPreferences()
                Setting.SUBTITLE -> backStack.navigateToSubtitlePreferences()
                Setting.GENERAL -> backStack.navigateToGeneralPreferences()
                Setting.ABOUT -> backStack.navigateToAboutPreferences()
            }
        },
    )
    appearancePreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    mediaLibraryPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onFolderSettingClick = backStack::navigateToFolderPreferencesScreen,
        onThumbnailSettingClick = backStack::navigateToThumbnailPreferencesScreen,
    )
    thumbnailPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    folderPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    playerPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    gesturePreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    audioPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    subtitlePreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    generalPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    aboutPreferencesEntry(
        onLibrariesClick = backStack::navigateToLibraries,
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    librariesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
}
