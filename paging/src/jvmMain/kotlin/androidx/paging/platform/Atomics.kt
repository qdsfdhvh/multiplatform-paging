package androidx.paging.platform

actual class AtomicBoolean actual constructor(value: Boolean) {

    private val delegate = java.util.concurrent.atomic.AtomicBoolean(value)

    actual fun get() = delegate.get()

    actual fun set(value: Boolean) {
        delegate.set(value)
    }

    actual fun compareAndSet(expected: Boolean, new: Boolean): Boolean {
        return delegate.compareAndSet(expected, new)
    }
}

actual class AtomicInteger actual constructor(value: Int) {

    private val delegate = java.util.concurrent.atomic.AtomicInteger(value)

    actual fun decrementAndGet() = delegate.decrementAndGet()
}
