package androidx.paging

/**
 * Interface for a factory that generates [PagingSource].
 *
 * The factory extending this interface can be used to instantiate a [Pager] as the
 * pagingSourceFactory.
 */
public fun interface PagingSourceFactory<Key : Any, Value : Any> : () -> PagingSource<Key, Value> {
    /**
     * Returns a new PagingSource instance.
     *
     * This function can be invoked by calling pagingSourceFactory() or pagingSourceFactory::invoke.
     */
    public override operator fun invoke(): PagingSource<Key, Value>
}
