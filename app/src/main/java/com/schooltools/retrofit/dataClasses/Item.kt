package com.schooltools.retrofit.dataClasses

data class Item(
    val hasSubnews: Boolean,
    val images: Images,
    val newsUrl: String,
    val publisher: String,
    val snippet: String,
    val subnews: List<Subnew>,
    val timestamp: String,
    val title: String
)