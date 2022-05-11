package androidx.paging.platform


expect class LockObject

expect inline fun <R> synchronized(lock: LockObject, block: () -> R): R