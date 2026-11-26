package com.novaplayer.x.core.media

import com.novaplayer.x.core.common.di.DispatchersModule
import com.novaplayer.x.core.database.DatabaseModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DatabaseModule::class, DispatchersModule::class])
@ComponentScan
class MediaModule
