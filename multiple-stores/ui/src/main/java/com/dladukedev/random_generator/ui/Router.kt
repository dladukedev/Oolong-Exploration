package com.dladukedev.random_generator.ui

import androidx.compose.Composable
import com.dladukedev.random_generator.store.Message
import com.dladukedev.random_generator.store.RootStore
import com.dladukedev.random_generator.store.Routes
import oolong.Dispatch

@Composable
fun Router(store: RootStore.Props, dispatch: Dispatch<Message>) {
    when (store.router.currentRoute) {
        is Routes.Home -> {
            HomeView(store = store, dispatch = dispatch)
        }
        is Routes.RandomLetter -> {
            RandomLetterView(store = store, dispatch = dispatch)
        }
        is Routes.RandomNumber -> {
            RandomNumberView(store = store, dispatch = dispatch)
        }
    }
}