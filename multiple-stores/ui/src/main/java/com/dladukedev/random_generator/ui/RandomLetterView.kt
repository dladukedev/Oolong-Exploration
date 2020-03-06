package com.dladukedev.random_generator.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.unit.dp
import com.dladukedev.random_generator.store.LetterStore
import com.dladukedev.random_generator.store.Message
import com.dladukedev.random_generator.store.RootStore
import oolong.Dispatch

@Composable
fun RandomLetter(randomLetter: LetterStore.LetterResult) {
    val additionalText = when (randomLetter) {
        is LetterStore.LetterResult.Empty -> "Not Generated"
        is LetterStore.LetterResult.Content -> randomLetter.data.toString()
        is LetterStore.LetterResult.Error -> randomLetter.message
        is LetterStore.LetterResult.Loading -> "Loading..."
    }
    
    Text("Random Letter - $additionalText")
}

@Composable
fun RandomLetterView(store: RootStore.Props, dispatch: Dispatch<Message>) {
    Center {
        Column {
            Button(onClick = { dispatch(store.router.navigateToHomeView()) }, modifier = LayoutGravity.Center) {
                Text("Return Home")
            }
            Spacer(modifier = LayoutHeight(16.dp))
            RandomLetter(randomLetter = store.letter.randomLetter)
            Spacer(modifier = LayoutHeight(12.dp))

            Button(onClick = { dispatch(store.letter.generateRandomLetter()) }, modifier = LayoutGravity.Center) {
                Text("Get Letter")
            }
        }
    }
}