package com.collector.presentation.util.extension

import com.geek.presentation.application.MainApplication

inline val Int.toResString: String get() = MainApplication.resource().getString(this)
