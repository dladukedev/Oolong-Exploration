package com.dladukedev.news.store

sealed class Routes {
    object ArticleList: Routes()
    data class ArticleDetails(val articleId: Int): Routes()
}