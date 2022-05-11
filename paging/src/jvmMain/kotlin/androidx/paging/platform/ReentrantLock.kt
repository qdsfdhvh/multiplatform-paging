package androidx.paging.platform

import kotlin.concurrent.withLock

actual class ReentrantLock {

    val delegate = java.util.concurrent.locks.ReentrantLock()

    actual fun lock() {
        delegate.lock()
    }

    actual fun unlock() {
        delegate.unlock()
    }

    actual inline fun <R> withLock(action: () -> R): R {
        return delegate.withLock(action)
    }
}