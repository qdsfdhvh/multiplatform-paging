package androidx.paging.platform

expect class IdentityHashMap<K, V>() {
    val lockObject: LockObject
    infix operator fun get(key: K): V?
    operator fun set(key: K, value: V)
}
