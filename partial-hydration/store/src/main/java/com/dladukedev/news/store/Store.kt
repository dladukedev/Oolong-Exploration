package com.dladukedev.news.store

import com.dladukedev.news.data.ArticleRepository
import com.dladukedev.news.domain.Article
import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect
import oolong.effect.none
import java.lang.Exception

object Store {
    sealed class ArticleResult {
        object Loading : ArticleResult()
        data class Error(val message: String) : ArticleResult()
        data class Content(val data: Article) : ArticleResult()
    }

    sealed class ArticleListResult {
        object Loading : ArticleListResult()
        data class Error(val message: String) : ArticleListResult()
        data class Content(val data: List<Article>) : ArticleListResult()
    }

    data class Model(
        val route: Routes = Routes.ArticleList,
        val article: ArticleResult = ArticleResult.Loading,
        val articles: ArticleListResult = ArticleListResult.Loading
    )

    sealed class Msg {
        object LoadArticleList : Msg()
        data class LoadArticleListSuccess(val data: List<Article>) : Msg()
        data class LoadArticleListError(val message: String) : Msg()

        data class LoadArticle(val articleId: Int) : Msg()
        data class LoadArticleSuccess(val data: Article) : Msg()
        data class LoadArticleError(val message: String) : Msg()

        object ClearArticle: Msg()

        data class ChangeRoute(val route: Routes): Msg()
    }

    private fun loadArticleList() = effect<Msg> { dispatch ->
        try {
            val articles = ArticleRepository.getArticleList()
            dispatch(Msg.LoadArticleListSuccess(articles))
        } catch (e: Exception) {
            dispatch(Msg.LoadArticleListError(e.message ?: "UNKNOWN EXCEPTION"))
        }
    }

    private fun loadArticle(articleId: Int) = effect<Msg> { dispatch ->
        try {
            val result = when (val article = ArticleRepository.getArticle(articleId)) {
                is Article -> Msg.LoadArticleSuccess(article)
                null -> Msg.LoadArticleError("No Article Found with Id: $articleId")
                else -> throw Exception("How did we get here?")
            }

            dispatch(result)
        } catch (e: Exception) {
            dispatch(Msg.LoadArticleListError(e.message ?: "UNKNOWN EXCEPTION"))
        }
    }

    class Props(
        val currentRoute: Routes,
        val goToArticleList: () -> Msg,
        val goToArticleDetails: (Int) -> Msg,
        val clearArticle: () -> Msg,
        val getArticles: () -> Msg,
        val getArticle: (Int) -> Msg,
        val articles: ArticleListResult,
        val article: ArticleResult
    )

    val init: Init<Model, Msg> = {
        Model() to none()
    }

    val update: Update<Model, Msg> = { msg, model ->
        when (msg) {
            Msg.LoadArticleList -> {
                model.copy(articles = ArticleListResult.Loading) to loadArticleList()
            }
            is Msg.LoadArticleListSuccess -> {
                model.copy(articles = ArticleListResult.Content(msg.data)) to none()
            }
            is Msg.LoadArticleListError -> {
                model.copy(articles = ArticleListResult.Error(msg.message)) to none()
            }
            is Msg.LoadArticle -> {
                val updatedModel = if(model.articles is ArticleListResult.Content && model.articles.data.any { it.id == msg.articleId}) {
                    val article = model.articles.data.find { it.id == msg.articleId }!!
                    model.copy(article = ArticleResult.Content(article))
                } else {
                    model.copy(article = ArticleResult.Loading)
                }

                updatedModel to loadArticle(msg.articleId)
            }
            is Msg.LoadArticleSuccess -> {
                val updatedModel = if(model.route is Routes.ArticleDetails && model.route.articleId == msg.data.id) {
                    model.copy(article = ArticleResult.Content(msg.data))
                } else {
                    model
                }

                updatedModel to none()
            }
            is Msg.LoadArticleError -> {
                model.copy(article = ArticleResult.Error(msg.message)) to none()
            }
            is Msg.ClearArticle -> {
                model.copy(article = ArticleResult.Loading) to none()
            }
            is Msg.ChangeRoute -> {
                model.copy(route = msg.route) to none()
            }
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            model.route,
            { Msg.ChangeRoute(Routes.ArticleList) },
            { id -> Msg.ChangeRoute(Routes.ArticleDetails(id)) },
            { Msg.ClearArticle },
            { Msg.LoadArticleList },
            { id -> Msg.LoadArticle(id) },
            model.articles,
            model.article
        )
    }
}