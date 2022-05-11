package androidx.paging.platform

import kotlinx.atomicfu.locks.SynchronizedObject

actual typealias LockObject = SynchronizedObject

actual inline fun <R> synchronized(lock: LockObject, block: () -> R): R {
    return kotlinx.atomicfu.locks.synchronized(lock, block)
}
