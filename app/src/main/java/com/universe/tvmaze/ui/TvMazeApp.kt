package com.universe.tvmaze.ui

import android.app.Application
import com.universe.tvmaze.BuildConfig
import dagger.hilt.android.HiltAndroidApp

/**
 * Only used to config Hilt
 *
 */
@HiltAndroidApp
class TvMazeApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}