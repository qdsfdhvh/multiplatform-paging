package androidx.paging

import androidx.annotation.IntRange

/**
 * Stores an instance of the Logger interface implementation which is to be injected by
 * paging-runtime during runtime. This allows paging-common to add logs with
 * android.util.log as a non-android artifact
 */
public var LOGGER: Logger? = null

public const val LOG_TAG: String = "Paging"

// @JvmDefaultWithCompatibility
/**
 */
public interface Logger {
    public fun isLoggable(level: Int): Boolean
    public fun log(level: Int, message: String, tr: Throwable? = null)
}

/**
 */
public inline fun log(
    @IntRange(from = VERBOSE.toLong(), to = DEBUG.toLong()) level: Int,
    tr: Throwable? = null,
    block: () -> String,
) {
    val logger = LOGGER
    if (logger?.isLoggable(level) == true) {
        logger.log(level, block(), tr)
    }
}

/**
 */
public const val VERBOSE: Int = 2

/**
 */
public const val DEBUG: Int = 3
