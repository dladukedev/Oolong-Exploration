package com.dladukedev.basic_example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.dladukedev.basic_example.store.Store.init
import com.dladukedev.basic_example.store.Store.update
import com.dladukedev.basic_example.store.Store.view
import com.dladukedev.basic_example.ui.MainView
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
                        MainView(props, dispatch)
                    }
                }
        )
    }
}
