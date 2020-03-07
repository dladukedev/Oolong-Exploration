package com.dladukedev.news.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.dladukedev.news.store.Store.init
import com.dladukedev.news.store.Store.update
import com.dladukedev.news.store.Store.view
import com.dladukedev.news.ui.Router
import oolong.Oolong

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Oolong.runtime(
            init,
            update,
            view,
            { props, dispatch ->
                setContent {
                    MaterialTheme {
                        Router(props, dispatch)
                    }
                }
            })
    }
}
