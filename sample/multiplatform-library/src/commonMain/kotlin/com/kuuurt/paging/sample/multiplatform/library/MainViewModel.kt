package com.kuuurt.paging.sample.multiplatform.library

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.kuuurt.paging.sample.multiplatform.library.helpers.asCommonFlow
import com.kuuurt.paging.sample.multiplatform.library.utils.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Copyright 2020, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 03/05/2020
 */

class MainViewModel : BaseViewModel() {
    private val fakeData = FakePositionalData()
    private val pageSize = 15

    val pager = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false,
            initialLoadSize = pageSize,
        ),
        initialKey = 1,
        pagingSourceFactory = {
            object : PagingSource<Int, String>() {
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
                    val page = params.key ?: 1
                    val items = fakeData.getData(page, pageSize)
                    return LoadResult.Page(
                        items,
                        prevKey = if (page < 0) null else page - 1,
                        nextKey = if (items.isEmpty()) null else page + 1,
                    )
                }

                override fun getRefreshKey(state: PagingState<Int, String>): Int? = null
            }
        }
    )

    private var _removedItemsFlow = MutableStateFlow(mutableListOf<String>())
    private val removedItemsFlow: Flow<MutableList<String>> get() = _removedItemsFlow

    val pagingData
        get() = pager.flow
            .cachedIn(clientScope)
            .asCommonFlow()

    fun removeItem(item: String) {
        var removedItems = _removedItemsFlow.value
        removedItems.add(item)
        removedItems = removedItems.distinctBy { it }.toMutableList()
        _removedItemsFlow.value = removedItems
    }

    class FakePositionalData {
        private val count = 100
        private val items = mutableListOf<String>()

        fun getCount() = count
        fun getData(startAt: Int, size: Int): List<String> {
            val list = mutableListOf<String>()
            var endSize = startAt + size
            if (endSize > count) {
                endSize = count
            }
            if (startAt < endSize) {
                for (i in startAt..endSize) {
                    list.add("Positional Test $i")
                }
                items.addAll(list)
            }
            return list
        }
    }

    class FakePagedData {
        private val count = 95
        private val items = mutableListOf<String>()

        fun getCount() = count
        fun getData(page: Int, size: Int): List<String> {
            val list = mutableListOf<String>()
            var startSize = (page - 1) * size
            var endSize = startSize + size
            if (endSize > count) {
                endSize = count
            }
            if (startSize < endSize) {
                for (i in startSize until endSize) {
                    list.add("Paged Test $i")
                }
                items.addAll(list)
            }
            return list
        }
    }
}