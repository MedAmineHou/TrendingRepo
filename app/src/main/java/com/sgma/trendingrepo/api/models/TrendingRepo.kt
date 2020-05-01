package com.sgma.trendingrepo.api.models

data class TrendingRepo(
    val incomplete_results: Boolean,
    val items: ArrayList<Item>,
    val total_count: Int
)