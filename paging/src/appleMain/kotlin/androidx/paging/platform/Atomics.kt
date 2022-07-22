package androidx.paging.platform

import kotlinx.atomicfu.atomic

actual class AtomicBoolean actual constructor(value: Boolean) {

    private val delegate = atomic(value)

    actual fun get() = delegate.value

    actual fun set(value: Boolean) {
        delegate.value = value
    }

    actual fun compareAndSet(expected: Boolean, new: Boolean): Boolean {
        return delegate.compareAndSet(expected, new)
    }
}

actual class AtomicInteger actual constructor(value: Int) {

    private val delegate = atomic(value)

    actual fun decrementAndGet() = delegate.decrementAndGet()
}
