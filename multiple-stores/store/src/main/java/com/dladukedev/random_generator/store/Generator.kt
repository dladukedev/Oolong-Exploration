package com.dladukedev.random_generator.store

import kotlin.random.Random

fun generateNumber(): Int = Random
    .nextInt(100)

fun generateLetter(): Char = Random
    .nextInt(65, 91)
    .toChar()