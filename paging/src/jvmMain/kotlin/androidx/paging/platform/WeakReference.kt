package androidx.paging.platform

actual class WeakReference<T : Any> actual constructor(referred: T) {

    private val delegate = java.lang.ref.WeakReference(referred)

    actual fun clear() = delegate.clear()

    actual fun get(): T? = delegate.get()
}
