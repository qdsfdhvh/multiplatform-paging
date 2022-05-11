package androidx.paging.platform

import kotlinx.coroutines.CoroutineDispatcher

expect val defaultDispatcher: CoroutineDispatcher

expect val ioDispatcher: CoroutineDispatcher
