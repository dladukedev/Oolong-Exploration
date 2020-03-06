package com.dladukedev.random_generator.store

import kotlinx.coroutines.delay
import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect
import oolong.effect.none


object NumberStore {
    sealed class NumberResult {
        object Empty: NumberResult()
        object Loading: NumberResult()
        data class Error(val message: String): NumberResult()
        data class Content(val data: Int): NumberResult()
    }

    data class Model(
        val randomNumber: NumberResult = NumberResult.Empty
    )

    sealed class Msg: Message {
        object GenerateRandomNumber : Msg()
        data class GenerateRandomNumberSuccess(val number: Int) : Msg()
        data class GenerateRandomNumberError(val message: String) : Msg()
    }

    private val generateNewNumber = effect<Msg>{ dispatch ->
        delay(500)
        try {
            val result = generateNumber()
            dispatch(Msg.GenerateRandomNumberSuccess(result))
        } catch (e: Exception) {
            dispatch(Msg.GenerateRandomNumberError(e.message ?: "UNKNOWN EXCEPTION"))
        }
    }

    class Props(
        val generateRandomNumber: () -> Msg,
        val randomNumber: NumberResult
    )

    val init: Init<Model, Msg> = {
        Model() to none()
    }

    val update: Update<Model, Msg> = { msg, model ->
        when (msg) {
            is Msg.GenerateRandomNumber -> {
                model.copy(randomNumber = NumberResult.Loading) to generateNewNumber
            }
            is Msg.GenerateRandomNumberError -> {
                model.copy(randomNumber = NumberResult.Error(msg.message)) to none()
            }
            is Msg.GenerateRandomNumberSuccess -> {
                model.copy(randomNumber = NumberResult.Content(msg.number)) to none()
            }
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            { Msg.GenerateRandomNumber },
            model.randomNumber
        )
    }
}