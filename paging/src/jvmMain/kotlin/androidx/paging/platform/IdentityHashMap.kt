package androidx.paging.platform

actual class IdentityHashMap<K, V> actual constructor() {

    private val delegate = java.util.IdentityHashMap<K, V>()

    actual val lockObject = LockObject()

    actual infix operator fun get(key: K): V? {
        return delegate[key]
    }

    actual operator fun set(key: K, value: V) {
        delegate[key] = value
    }
}