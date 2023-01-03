package com.example.metbrowser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Basis for automatic dependency injection.
 *   = parent container for all injected dependencies
 */
@HiltAndroidApp
class MetBrowserApplication : Application()