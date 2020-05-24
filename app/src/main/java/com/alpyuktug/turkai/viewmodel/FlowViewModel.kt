package com.alpyuktug.turkai.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpyuktug.turkai.model.PostList
import com.alpyuktug.turkai.service.API
import com.alpyuktug.turkai.service.APIService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FlowViewModel : ViewModel() {

    private val api = APIService()
    private val disposable = CompositeDisposable()

    val postlist = MutableLiveData<List<PostList>>()
    val postlisterror = MutableLiveData<Boolean>()
    val postlistloading = MutableLiveData<Boolean>()

    fun RefreshFlow()
    {
        getPostFromAPI()

    }

    private fun getPostFromAPI()
    {
        postlistloading.value = true

        disposable.add(
            api.getPostList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PostList>>(){
                    override fun onSuccess(t: List<PostList>) {
                        postlist.value = t
                        postlisterror.value = false
                        postlistloading.value = false
                    }

                    override fun onError(e: Throwable) {
                        postlisterror.value = false
                        postlistloading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

}