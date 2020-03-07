package com.dladukedev.news.data

import com.dladukedev.news.domain.Article

private const val CONTENT = "Semper fames vivamus praesent metus turpis elementum urna faucibus aliquet. Congue fames morbi scelerisque cursus senectus ad ullamcorper accumsan curabitur eros tempor vestibulum iaculis quis vestibulum lacus condimentum posuere purus habitant dictum torquent taciti at ultrices curabitur Sociis amet turpis. Habitasse elit duis. Turpis curae; luctus a ornare. Sem senectus urna imperdiet. Semper. Quisque porttitor tristique tincidunt et nulla sagittis eleifend eleifend mus interdum dui. Maecenas montes vestibulum metus venenatis. Dictum proin convallis Convallis lorem, vestibulum.\n" +
        "\n" +
        "Sit. Amet, ante magnis urna aliquam gravida. Magnis penatibus nulla enim a id euismod arcu. Praesent At aliquam parturient. Libero est velit nibh sem arcu risus vulputate tempor aliquet nunc suscipit elementum curae; nunc neque sit facilisi magnis. Vel integer torquent, senectus suspendisse augue nullam consectetuer dapibus. Rhoncus quis sociosqu lobortis quam Tortor dis interdum pulvinar vivamus tortor nullam natoque in fringilla purus. Ac neque morbi tincidunt Netus mattis tristique scelerisque donec leo magnis tristique sem cras iaculis nec aliquet fringilla varius enim eleifend vel velit taciti erat vel nonummy posuere facilisis scelerisque rhoncus luctus. Sem habitant orci pulvinar pretium litora. Nec leo lacus, vel magna nibh gravida metus sociis. Ligula rutrum tincidunt erat cubilia.\n" +
        "\n" +
        "Parturient maecenas eu. Integer platea hymenaeos fermentum purus litora habitant vitae. Pretium phasellus. Auctor tristique. Quisque etiam gravida elit donec pellentesque egestas urna ut suspendisse viverra porta magna bibendum nonummy urna condimentum nibh dictumst sociosqu Egestas blandit hendrerit purus curae; taciti ante conubia imperdiet. Parturient bibendum parturient cursus euismod. Urna purus ridiculus, metus cras ipsum feugiat pretium dictum turpis vehicula in augue pellentesque. Orci pede mattis imperdiet pulvinar facilisi dolor molestie consectetuer luctus. Leo dignissim quis vitae id suspendisse fringilla phasellus diam donec eleifend donec purus tortor arcu fringilla dictumst. Ultricies pharetra ad, nunc litora faucibus id ipsum porta. Non facilisi quam nibh ultrices sit. Scelerisque arcu nullam ultricies."

private val articles = listOf<Article>(
    Article.Full( 1,"Article Title 1", CONTENT),
    Article.Full(2,"Article Title 2", CONTENT),
    Article.Full(3,"Article Title 3", CONTENT),
    Article.Full(4,"Article Title 4", CONTENT),
    Article.Full(5,"Article Title 5", CONTENT),
    Article.Full(6,"Article Title 6", CONTENT),
    Article.Full(7,"Article Title 7", CONTENT),
    Article.Full(8,"Article Title 8", CONTENT),
    Article.Full(9,"Article Title 9", CONTENT),
    Article.Full(10,"Article Title 10", CONTENT),
    Article.Full(11,"Article Title 11", CONTENT),
    Article.Full(12,"Article Title 12", CONTENT),
    Article.Full(13,"Article Title 13", CONTENT),
    Article.Full(14,"Article Title 14", CONTENT),
    Article.Full(15,"Article Title 15", CONTENT),
    Article.Full(16,"Article Title 16", CONTENT),
    Article.Full(17,"Article Title 17", CONTENT),
    Article.Full(18,"Article Title 18", CONTENT),
    Article.Full(19,"Article Title 19", CONTENT),
    Article.Full(20,"Article Title 20", CONTENT),
    Article.Full(21,"Article Title 21", CONTENT),
    Article.Full(22,"Article Title 22", CONTENT),
    Article.Full(23,"Article Title 23", CONTENT),
    Article.Full(24,"Article Title 24", CONTENT),
    Article.Full(25,"Article Title 25", CONTENT)
)

object ArticleWebApi {


    fun getArticleList(): List<Article.Partial> = articles.map { Article.Partial(it.id, it.title) }
    fun getArticle(id: Int): Article.Full? = articles.find { it.id == id } as Article.Full
}