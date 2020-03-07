package com.dladukedev.news.ui

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.unit.dp
import com.dladukedev.news.domain.Article
import com.dladukedev.news.store.Store
import oolong.Dispatch

@Composable
fun ArticleListItem(article: Article, goToDetails: (id: Int) -> Unit) {
    Ripple(bounded = true) {
        Clickable(onClick = { goToDetails(article.id) }) {
            Container(padding = EdgeInsets(16.dp), modifier = LayoutWidth.Fill, alignment = Alignment.CenterStart) {
                Text(
                    article.title,
                    style = MaterialTheme.typography().h6
                )
            }
        }
    }
}

@Composable
fun ArticleListView(store: Store.Props, dispatch: Dispatch<Store.Msg>) {
    onActive(callback = { dispatch(store.getArticles()) })
    Column(modifier = LayoutWidth.Fill) {
        TopAppBar(
            title = { Text("News") }
        )
        VerticalScroller {
            Column(modifier = LayoutWidth.Fill) {
                when (val articles = store.articles) {
                    is Store.ArticleListResult.Content -> {
                        articles.data.map {
                            ArticleListItem(
                                article = it,
                                goToDetails = { id -> dispatch(store.goToArticleDetails(id)) })
                        }
                    }
                    is Store.ArticleListResult.Error -> {
                        Text(articles.message)
                    }
                    is Store.ArticleListResult.Loading -> {
                        Text("Loading...")
                    }
                }
            }
        }
    }
}