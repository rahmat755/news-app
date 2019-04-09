package com.arthur.newsapp.util

import timber.log.Timber

class TimberTree: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return  String.format("%s#%s:%s",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber)
    }
}