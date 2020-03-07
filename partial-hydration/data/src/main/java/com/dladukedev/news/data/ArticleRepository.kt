package com.dladukedev.news.data

import com.dladukedev.news.domain.Article
import kotlinx.coroutines.delay

object ArticleRepository {
    suspend fun getArticleList(): List<Article> {
        return when(ArticleCache.hasArticles()) {
            true -> {
                ArticleCache.getArticles()
            }
            false -> {
                delay(1000)
                val articles = ArticleWebApi.getArticleList()
                ArticleCache.setArticles(articles)
                articles
            }
        }
    }

    private suspend fun getArticleFromWebApi(id: Int): Article.Full? {
        delay(1000)
        val updatedArticle = ArticleWebApi.getArticle(id)
        if(updatedArticle != null) {
            ArticleCache.setArticle(updatedArticle)
        }
        return updatedArticle
    }

    suspend fun getArticle(id: Int): Article.Full? {
        return when(ArticleCache.hasArticle(id)) {
            true -> {
                when(val article = ArticleCache.getArticle(id)) {
                    is Article.Full -> article
                    is Article.Partial -> {
                        getArticleFromWebApi(id)
                    }
                }
            }
            false -> {
                getArticleFromWebApi(id)
            }
        }
    }
}