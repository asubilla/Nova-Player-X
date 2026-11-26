package com.novaplayer.x

import com.novaplayer.x.core.data.repository.NetworkConnectionRepository
import com.novaplayer.x.core.media.network.keys.SshKeyStore
import com.novaplayer.x.core.model.NetworkAuthentication
import com.novaplayer.x.core.model.NetworkProtocol
import kotlinx.coroutines.flow.first

internal suspend fun initializeSshKeyStore(
    repository: NetworkConnectionRepository,
    sshKeyStore: SshKeyStore,
) {
    val referencedFileNames = try {
        repository.getConnections().first()
            .asSequence()
            .filter { connection ->
                connection.protocol == NetworkProtocol.SFTP &&
                    connection.authentication == NetworkAuthentication.SSH_KEY
            }
            .mapNotNull { connection ->
                connection.privateKeyFileName.trim()
                    .takeIf(predicate = SshKeyStore::isValidFileName)
            }
            .toSet()
    } catch (_: Throwable) {
        null
    }
    sshKeyStore.initialize(referencedFileNames = referencedFileNames)
}
