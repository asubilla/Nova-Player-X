package com.novaplayer.x.settings

import com.novaplayer.x.core.domain.DomainModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DomainModule::class])
@ComponentScan
class SettingsModule
