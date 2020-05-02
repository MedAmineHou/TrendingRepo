package com.sgma.trendingrepo.views.trending

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sgma.trendingrepo.R
import com.sgma.trendingrepo.adapters.TrendingRepoAdapter
import com.sgma.trendingrepo.api.models.TrendingRepo
import kotlinx.android.synthetic.main.activity_trending.*

class TrendingActivity : AppCompatActivity(), TrendingContract.TrendingView {

    private lateinit var mTrendingPresenter: TrendingPresenter
    private lateinit var mTrendingRepo: TrendingRepo
    private lateinit var mTrendingRepoAdapter: TrendingRepoAdapter
    private var mPage = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)

        this.mTrendingPresenter = TrendingPresenter()
        this.mTrendingPresenter.attachView(this)
        this.loadItems()

        trending_repo_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleElement: LinearLayoutManager =
                    trending_repo_list.layoutManager as LinearLayoutManager
                if (!isLoading && lastVisibleElement.findLastCompletelyVisibleItemPosition() == mTrendingRepo.items.size - 1) {
                    loadItems()
                    isLoading = true
                }
            }
        })
    }

    private fun loadItems() {
        this.mTrendingPresenter.onDoGetTrendingRepo(mPage)
    }


    override fun doPopulateTrendingRepo(trendingRepo: TrendingRepo) {
        when (isLoading) {
            true -> {
                mTrendingRepo.items.addAll(trendingRepo.items)
                mTrendingRepoAdapter.notifyDataSetChanged()
                this.isLoading = false
            }
            false -> {
                mTrendingRepo = trendingRepo
                this.mTrendingRepoAdapter = TrendingRepoAdapter(mTrendingRepo.items, this)
                trending_repo_list.adapter = mTrendingRepoAdapter
                trending_repo_list.layoutManager = LinearLayoutManager(this)
            }
        }

        this.mPage += 1
    }

    override fun showProgress() {
        trending_repo_progress.visibility = View.VISIBLE
        header_container.visibility = View.GONE
        footer_trending_container.visibility = View.GONE
    }

    override fun hideProgress() {
        trending_repo_progress.visibility = View.GONE
        header_container.visibility = View.VISIBLE
        footer_trending_container.visibility = View.VISIBLE
    }
}
