package com.dladukedev.news.ui

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.compose.onDispose
import androidx.compose.onPreCommit
import androidx.ui.core.Text
import androidx.ui.foundation.Icon
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Spacer
import androidx.ui.material.Button
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Close
import androidx.ui.unit.dp
import com.dladukedev.news.domain.Article
import com.dladukedev.news.store.Store
import oolong.Dispatch

@Composable
fun ArticleDetailsView(store: Store.Props, dispatch: Dispatch<Store.Msg>, id: Int) {
    onActive(callback = { dispatch(store.getArticle(id)) })
    onDispose(callback = { dispatch(store.clearArticle()) })

    Column {
        TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { dispatch(store.goToArticleList()) }) {
                        Icon(Icons.Default.Close)
                    }
                },
                title = { Text("Article Details")}
                )
        VerticalScroller {
            Column(modifier = LayoutPadding(16.dp)) {
                when (val articleResult = store.article) {
                    is Store.ArticleResult.Content -> {
                        val article = articleResult.data
                        Text(article.title, style = MaterialTheme.typography().h4)
                        Spacer(modifier = LayoutHeight(16.dp))
                        when (article) {
                            is Article.Full -> {
                                Text(article.content)
                            }
                            is Article.Partial -> {
                                Text("Loading...")
                            }
                        }
                    }
                    is Store.ArticleResult.Error -> {
                        Text(articleResult.message)
                    }
                    is Store.ArticleResult.Loading -> {
                        Text("Loading...")
                    }
                }
            }
        }
    }
}