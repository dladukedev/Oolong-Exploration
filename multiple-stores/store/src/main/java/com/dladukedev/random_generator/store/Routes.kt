package com.dladukedev.random_generator.store

sealed class Routes {
    object Home: Routes()
    object RandomNumber: Routes()
    object RandomLetter: Routes()
}