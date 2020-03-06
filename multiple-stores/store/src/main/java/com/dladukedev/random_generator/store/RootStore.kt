package com.dladukedev.random_generator.store

import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect.batch
import oolong.effect.none

object RootStore {
    data class Model(
        val router: RouterStore.Model,
        val letter: LetterStore.Model,
        val number: NumberStore.Model
    )

    class Props(
        val router: RouterStore.Props,
        val letter: LetterStore.Props,
        val number: NumberStore.Props
    )

    val init: Init<Model, Message> = {
        val (router, routerNext) = RouterStore.init()
        val (letter, letterNext) = LetterStore.init()
        val (number, numberNext) = NumberStore.init()

        val initializeStores = batch(
            routerNext,
            letterNext,
            numberNext
        )

        Model(router, letter, number) to initializeStores
    }

    val update: Update<Model, Message> = { msg, model ->
        when (msg) {
            is RouterStore.Msg -> {
                val (result, next) = RouterStore.update(msg, model.router)
                model.copy(router = result) to next
            }
            is NumberStore.Msg -> {
                val (result, next) = NumberStore.update(msg, model.number)
                model.copy(number = result) to next
            }
            is LetterStore.Msg -> {
                val (result, next) = LetterStore.update(msg, model.letter)
                model.copy(letter = result) to next
            }
            else -> {
                model to none()
            }
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            RouterStore.view(model.router),
            LetterStore.view(model.letter),
            NumberStore.view(model.number)
        )
    }
}