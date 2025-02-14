package androidx.paging.platform

import kotlinx.atomicfu.locks.withLock

actual class ReentrantLock {

    val delegate = kotlinx.atomicfu.locks.ReentrantLock()

    actual inline fun <R> withLock(action: () -> R): R {
        return delegate.withLock(action)
    }
}
