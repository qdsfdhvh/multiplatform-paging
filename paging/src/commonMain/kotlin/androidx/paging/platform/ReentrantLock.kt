package androidx.paging.platform

expect class ReentrantLock() {
    fun lock()
    fun unlock()
    inline fun <R> withLock(action: () -> R): R
}