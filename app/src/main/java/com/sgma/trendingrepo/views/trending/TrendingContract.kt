package com.sgma.trendingrepo.views.trending

import com.sgma.trendingrepo.api.models.TrendingRepo

interface TrendingContract {

    interface ViewActions {
        fun onDoGetTrendingRepo(mPage: Int)
    }

    interface TrendingView {
        fun doPopulateTrendingRepo(trendingRepo: TrendingRepo)
        fun showProgress()
        fun hideProgress()
    }
}