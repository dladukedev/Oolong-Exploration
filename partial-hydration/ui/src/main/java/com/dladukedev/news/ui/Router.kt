package com.dladukedev.news.ui

import androidx.compose.Composable
import com.dladukedev.news.store.Routes
import com.dladukedev.news.store.Store
import oolong.Dispatch

@Composable
fun Router(store: Store.Props, dispatch: Dispatch<Store.Msg>) {
    when(val route = store.currentRoute) {
        is Routes.ArticleList -> ArticleListView(store, dispatch)
        is Routes.ArticleDetails -> ArticleDetailsView(store, dispatch, route.articleId)
    }
}