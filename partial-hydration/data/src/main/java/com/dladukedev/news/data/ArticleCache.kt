package com.dladukedev.news.data

import com.dladukedev.news.domain.Article

object ArticleCache {
    private var articles: Map<Int, Article> = emptyMap()

    fun hasArticles(): Boolean = articles.isNotEmpty()
    fun getArticles(): List<Article> = articles.map { it.value }
    fun setArticles(updatedArticles: List<Article>) {
        articles = updatedArticles.map { it.id to it }.toMap()
    }

    fun hasArticle(articleId: Int): Boolean = articles.containsKey(articleId)
    fun getArticle(articleId: Int): Article = articles[articleId] ?: throw Exception("Always check for article before retrieving")
    fun setArticle(article: Article) {
        val updatedArticles = articles.toMutableMap()
        updatedArticles[article.id] = article
        articles = updatedArticles
    }
}