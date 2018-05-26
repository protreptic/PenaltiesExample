package test.p00.auxiliary.dependecies.modules

import dagger.Module
import dagger.Provides
import test.p00.data.remote.authorize.Authorize
import test.p00.data.storage.websocket.WebSocketConnection

/**
 * Created by Peter Bukhal on 5/26/18.
 */
@Module(includes = [  ])
class NetworkModule {

    @Provides
    fun provideAuthorize(): Authorize = Authorize.api

    @Provides
    fun provideWebSocketConnection(): WebSocketConnection = WebSocketConnection.connection

}