package com.sgma.trendingrepo.views.base

import androidx.annotation.NonNull
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V> {

    protected var mView: V? = null

    protected val isViewAttached: Boolean
    get() = mView != null

    fun attachView(@NonNull view: V){
        mView = view
    }

    abstract fun cancelDisposable()

    companion object{
        fun cleanDisposable(disposables: ArrayList<Disposable>){
            for(disposable in disposables){
                disposable.dispose()
            }

            disposables.clear()
        }
    }
}