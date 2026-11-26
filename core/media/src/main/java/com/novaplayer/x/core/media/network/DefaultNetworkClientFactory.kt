package com.novaplayer.x.core.media.network

import com.novaplayer.x.core.media.network.clients.FtpClient
import com.novaplayer.x.core.media.network.clients.SftpClient
import com.novaplayer.x.core.media.network.clients.SmbClient
import com.novaplayer.x.core.media.network.clients.WebDavClient
import com.novaplayer.x.core.media.network.keys.SshKeyStore
import com.novaplayer.x.core.model.NetworkConnection
import com.novaplayer.x.core.model.NetworkProtocol
import org.koin.core.annotation.Single

@Single
class DefaultNetworkClientFactory(private val sshKeyStore: SshKeyStore) : NetworkClientFactory {
    override fun create(connection: NetworkConnection): NetworkClient = when (connection.protocol) {
        NetworkProtocol.SMB -> SmbClient(connection = connection)

        NetworkProtocol.FTP -> FtpClient(connection = connection)

        NetworkProtocol.SFTP -> SftpClient(
            connection = connection,
            sshKeyStore = sshKeyStore,
        )

        NetworkProtocol.WEBDAV -> WebDavClient(connection = connection)
    }
}
