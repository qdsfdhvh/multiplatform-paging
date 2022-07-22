package androidx.paging.platform

expect class CopyOnWriteArrayList<T>() {
    fun forEach(action: (T) -> Unit)
    fun add(item: T)
    fun remove(item: T)
    fun removeAll(predicate: (T) -> Boolean)
    operator fun iterator(): Iterator<T>
}
