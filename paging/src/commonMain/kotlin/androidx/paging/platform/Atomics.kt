package androidx.paging.platform

expect class AtomicBoolean(value: Boolean) {
    fun get(): Boolean
    fun set(value: Boolean)
    fun compareAndSet(expected: Boolean, new: Boolean): Boolean
}

expect class AtomicInteger(value: Int) {
    fun decrementAndGet(): Int
}
