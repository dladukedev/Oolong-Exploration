package com.dladukedev.random_generator.store

import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect.none

object RouterStore {
    data class Model(
        val route: Routes = Routes.Home
    )

    sealed class Msg: Message {
        object GoToHomeView : Msg()
        object GoToRandomLetterView : Msg()
        object GoToRandomNumberView : Msg()
    }

    class Props(
        val navigateToHomeView: () -> Msg,
        val navigateToRandomLetterView: () -> Msg,
        val navigateToRandomNumberView: () -> Msg,
        val currentRoute: Routes
    )

    val init: Init<Model, Msg> = {
        Model() to none()
    }

    val update: Update<Model, Msg> = { msg, model ->
        when (msg) {
            is Msg.GoToHomeView -> {
                model.copy(route = Routes.Home) to none()
            }
            Msg.GoToRandomLetterView -> {
                model.copy(route = Routes.RandomLetter) to none()
            }
            Msg.GoToRandomNumberView -> {
                model.copy(route = Routes.RandomNumber) to none()
            }
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            { Msg.GoToHomeView },
            { Msg.GoToRandomLetterView },
            { Msg.GoToRandomNumberView },
            model.route
        )
    }
}