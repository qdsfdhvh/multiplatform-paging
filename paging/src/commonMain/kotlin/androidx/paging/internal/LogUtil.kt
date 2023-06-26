package androidx.paging.internal

import androidx.paging.LoadStates

internal inline fun appendMediatorStatesIfNotNull(
    mediatorStates: LoadStates?,
    log: () -> String,
): String {
    var newLog = log()
    if (mediatorStates != null) {
        newLog = newLog.plus("""|   mediatorLoadStates: $mediatorStates${"\n"}""")
    }
    return newLog.plus("|)").trimMargin()
}
