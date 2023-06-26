package androidx.paging.platform

expect class ReentrantLock() {
    inline fun <R> withLock(action: () -> R): R
}
