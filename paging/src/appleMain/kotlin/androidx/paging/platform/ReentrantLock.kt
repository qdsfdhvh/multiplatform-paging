package androidx.paging.platform

import kotlinx.atomicfu.locks.withLock

actual class ReentrantLock {

    val delegate = kotlinx.atomicfu.locks.ReentrantLock()

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
