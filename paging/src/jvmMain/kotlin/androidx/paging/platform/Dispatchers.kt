package androidx.paging.platform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val defaultDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default

actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO
