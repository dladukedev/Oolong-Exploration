package com.dladukedev.random_generator.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.unit.dp
import com.dladukedev.random_generator.store.*
import oolong.Dispatch


@Composable
fun RandomNumber(randomNumber: NumberStore.NumberResult) {
    val additionalText = when (randomNumber) {
        is NumberStore.NumberResult.Empty -> "Not Generated"
        is NumberStore.NumberResult.Content -> randomNumber.data.toString()
        is NumberStore.NumberResult.Error -> randomNumber.message
        is NumberStore.NumberResult.Loading -> "Loading..."
    }

    Text("Random Number - $additionalText")
}


@Composable
fun RandomNumberView(store: RootStore.Props, dispatch: Dispatch<Message>) {
    Center {
        Column {
            Button(onClick = { dispatch(store.router.navigateToHomeView()) }, modifier = LayoutGravity.Center) {
                Text("Return Home")
            }
            Spacer(modifier = LayoutHeight(16.dp))
            RandomNumber(randomNumber = store.number.randomNumber)
            Spacer(modifier = LayoutHeight(12.dp))

            Button(onClick = { dispatch(store.number.generateRandomNumber()) }, modifier = LayoutGravity.Center) {
                Text("Get Number")
            }
        }
    }
}