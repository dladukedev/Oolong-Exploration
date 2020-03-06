package com.dladukedev.random_generator.ui

import androidx.compose.Composable
import androidx.ui.material.MaterialTheme
import com.dladukedev.random_generator.store.Message
import com.dladukedev.random_generator.store.RootStore
import oolong.Dispatch

@Composable
fun App(store: RootStore.Props, dispatch: Dispatch<Message>) {
    MaterialTheme {
        Router(store = store, dispatch = dispatch)
    }
}