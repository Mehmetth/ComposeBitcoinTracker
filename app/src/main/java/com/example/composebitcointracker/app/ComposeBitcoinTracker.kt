package com.example.composebitcointracker.app

import android.app.Application
import androidx.compose.runtime.Stable
import dagger.hilt.android.HiltAndroidApp

@Stable
@HiltAndroidApp
class ComposeBitcoinTracker : Application()