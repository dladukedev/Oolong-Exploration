package com.dladukedev.random_generator.store

import kotlinx.coroutines.delay
import oolong.Init
import oolong.Update
import oolong.View
import oolong.effect
import oolong.effect.none

object LetterStore {
    sealed class LetterResult {
        object Empty: LetterResult()
        object Loading: LetterResult()
        data class Error(val message: String): LetterResult()
        data class Content(val data: Char): LetterResult()
    }

    data class Model(
        val randomLetter: LetterResult = LetterResult.Empty
    )

    sealed class Msg: Message {
        object GenerateRandomLetter : Msg()
        data class GenerateRandomLetterSuccess(val Letter: Char) : Msg()
        data class GenerateRandomLetterError(val message: String) : Msg()
    }

    private val generateNewLetter = effect<Msg>{ dispatch ->
        delay(500)
        try {
            val result = generateLetter()
            dispatch(Msg.GenerateRandomLetterSuccess(result))
        } catch (e: Exception) {
            dispatch(Msg.GenerateRandomLetterError(e.message ?: "UNKNOWN EXCEPTION"))
        }
    }

    class Props(
        val generateRandomLetter: () -> Msg,
        val randomLetter: LetterResult
    )

    val init: Init<Model, Msg> = {
        Model() to none()
    }

    val update: Update<Model, Msg> = { msg, model ->
        when (msg) {
            is Msg.GenerateRandomLetter -> {
                model.copy(randomLetter = LetterResult.Loading) to generateNewLetter
            }
            is Msg.GenerateRandomLetterError -> {
                model.copy(randomLetter = LetterResult.Error(msg.message)) to none()
            }
            is Msg.GenerateRandomLetterSuccess -> {
                model.copy(randomLetter = LetterResult.Content(msg.Letter)) to none()
            }
        }
    }

    val view: View<Model, Props> = { model ->
        Props(
            { Msg.GenerateRandomLetter },
            model.randomLetter
        )
    }
}