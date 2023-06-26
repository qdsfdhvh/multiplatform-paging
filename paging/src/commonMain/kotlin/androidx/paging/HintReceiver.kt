package androidx.paging

/**
 * Fetcher-side callbacks for presenter-side access events communicated through [PagingData].
 */
internal interface HintReceiver {
    fun accessHint(viewportHint: ViewportHint)
}
