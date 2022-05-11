package androidx.paging.platform

actual class CopyOnWriteArrayList<T> actual constructor() {

    private val delegate = java.util.concurrent.CopyOnWriteArrayList<T>()

    actual fun forEach(action: (T) -> Unit) {
        delegate.forEach(action)
    }

    actual fun add(item: T) {
        delegate.add(item)
    }

    actual fun remove(item: T) {
        delegate.remove(item)
    }

    actual fun removeAll(predicate: (T) -> Boolean) {
        delegate.removeAll(predicate)
    }

    actual operator fun iterator(): Iterator<T> {
        return delegate.iterator()
    }
}