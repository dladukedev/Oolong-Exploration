package com.dladukedev.basic_example.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.dladukedev.basic_example.store.DataFetchResult
import com.dladukedev.basic_example.store.Store
import oolong.Dispatch

@Composable
fun DisplayResults(props: Store.Props) {
    val resultText = when (val result = props.data) {
        is DataFetchResult.Empty -> "Click to get Data!"
        is DataFetchResult.Loading -> "Loading..."
        is DataFetchResult.Error -> result.message
        is DataFetchResult.Content -> result.data
    }

    Text(text = resultText)
}

@Composable
fun MainView(props: Store.Props, dispatch: Dispatch<Store.Msg>) {
    MaterialTheme {
        Center {
            Column {
                DisplayResults(props)
                Spacer(modifier = LayoutHeight(10.dp))
                Button(
                    modifier = LayoutGravity.Center,
                    onClick = { dispatch(props.getData()) }
                ) {
                    Text("Get Data")
                }
            }
        }
    }
}