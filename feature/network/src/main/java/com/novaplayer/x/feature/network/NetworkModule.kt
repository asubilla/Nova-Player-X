package com.novaplayer.x.feature.network

import com.novaplayer.x.core.data.DataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DataModule::class])
@ComponentScan
class NetworkModule
