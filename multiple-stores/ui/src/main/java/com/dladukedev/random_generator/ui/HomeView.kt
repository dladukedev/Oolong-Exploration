package com.dladukedev.random_generator.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.unit.dp
import com.dladukedev.random_generator.store.Message
import com.dladukedev.random_generator.store.RootStore
import oolong.Dispatch

@Composable
fun HomeView(store: RootStore.Props, dispatch: Dispatch<Message>) {
    Center {
        Row {
            Button(onClick = { dispatch(store.router.navigateToRandomLetterView()) }) {
                Text("Random Letter")
            }
            Spacer(modifier = LayoutWidth(8.dp))
            Button(onClick = { dispatch(store.router.navigateToRandomNumberView()) }) {
                Text("Random Number")
            }
        }
    }
}