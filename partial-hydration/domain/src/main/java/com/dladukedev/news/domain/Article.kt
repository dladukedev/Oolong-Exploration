package com.dladukedev.news.domain

sealed class Article(val id: Int, val title: String) {
    data class Partial(private val _id: Int, private val _title: String): Article(id = _id, title = _title)
    data class Full(private val _id: Int, private val _title: String, val content: String): Article(id = _id, title = _title)
}