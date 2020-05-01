package com.sgma.trendingrepo.views.trending

import android.util.Log
import com.sgma.trendingrepo.BuildConfig
import com.sgma.trendingrepo.api.services.ServiceGenerator
import com.sgma.trendingrepo.api.services.TrendingApiService
import com.sgma.trendingrepo.views.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class TrendingPresenter : BasePresenter<TrendingContract.TrendingView>(),
    TrendingContract.ViewActions {

    private var disposables: ArrayList<Disposable> = arrayListOf()
    private var mTrendingService: TrendingApiService

    init {
        mTrendingService =
            ServiceGenerator.createService(
                TrendingApiService::class.java, BuildConfig.BASE_URL,
                TIMEOUT
            )
    }

    override fun cancelDisposable() {
        cleanDisposable(this.disposables)
    }

    override fun onDoGetTrendingRepo(mPage: Int) {
        if (!isViewAttached) return
        mView!!.showProgress()
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH)
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -30)

            val dateTrending = sdf.format(calendar.time)
            val disposable =
                mTrendingService.getTrendingRepo("created:>$dateTrending", page = mPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        if(!isViewAttached) return@subscribe
                        mView!!.doPopulateTrendingRepo(response)
                        mView!!.hideProgress()
                    },
                        {error ->
                            if(!isViewAttached) return@subscribe
                            mView!!.hideProgress()
                            Log.e("error trending repo", error.message!!)
                        })
            this.disposables.add(disposable)

        }catch (e: Exception){
            mView!!.hideProgress()
            Log.e("error trending repo", e.message!!)
        }
    }

    companion object {
        const val TIMEOUT = 60L
    }
}