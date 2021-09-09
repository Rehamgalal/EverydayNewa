package com.example.everydaynewa.viewModel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.everydaynewa.model.Article
import com.example.everydaynewa.model.datasource.NewsDataSource
import com.example.everydaynewa.network.RetroInstance
import com.example.everydaynewa.network.RetrofitApi

class MainActivityViewModel: ViewModel() {

     private var retrofitApi: RetrofitApi = RetroInstance.getRetrofitInstance()
    private var searchKey: MutableLiveData<String> = MutableLiveData()
    fun getListArticle(search:String): LiveData<PagingData<Article>> {
          return  Pager(config = PagingConfig(20,5,true,10,100)
                ,1,{NewsDataSource(retrofitApi,search)}).liveData

    }

    val article = searchKey.switchMap {
        getListArticle(it).cachedIn(viewModelScope)
    }
    fun setFilter(filter: String?) {
        if (filter == null) searchKey.value = "" else searchKey.value = filter
    }
}