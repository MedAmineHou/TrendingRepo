package com.sgma.trendingrepo.models

data class TrendingRepo(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)