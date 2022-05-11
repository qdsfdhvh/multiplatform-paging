package androidx.paging.platform

fun interface Function<I, O> {
    fun apply(input: I): O
}