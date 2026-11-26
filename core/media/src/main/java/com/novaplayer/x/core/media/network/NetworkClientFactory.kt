package com.novaplayer.x.core.media.network

import com.novaplayer.x.core.model.NetworkConnection

fun interface NetworkClientFactory {
    fun create(connection: NetworkConnection): NetworkClient
}
