package com.dladukedev.random_generator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.dladukedev.random_generator.store.RootStore.init
import com.dladukedev.random_generator.store.RootStore.update
import com.dladukedev.random_generator.store.RootStore.view
import com.dladukedev.random_generator.ui.App
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
                    App(props, dispatch)
                }
            })
    }
}
