package com.example.everydaynewa.model.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.everydaynewa.model.Article
import com.example.everydaynewa.model.NewsModel
import com.example.everydaynewa.network.RetrofitApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NewsDataSource(val api: RetrofitApi,val searchKey:String): RxPagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }


    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Article>> {
        val position = params.key ?: FIRST_PAGE_INDEX

        return api.getNewsToday("eg",position,searchKey).subscribeOn(Schedulers.io())
            .map {
            toLoadResult(it,position)
        }
            .onErrorReturn {
                LoadResult.Error(it) }

    }

    private fun toLoadResult(data: NewsModel, position: Int): LoadResult<Int,Article> {
        return LoadResult.Page(
            data = data.articles,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == 10) null else position + 1
        )
    }

}