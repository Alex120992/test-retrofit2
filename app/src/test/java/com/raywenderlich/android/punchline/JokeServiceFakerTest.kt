package com.raywenderlich.android.punchline

import com.github.javafaker.Faker
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

private const val id = "6"
private const val joke =
        "How does a train eat? It goes chew, chew"


class JokeServiceTestUsingFaker {
    @get:Rule
    private val testJson = """{ "id": 3, "joke": sdsds }"""
    var faker = Faker()
    private val jokeService: JokeService = mock()
    private val repository = RepositoryImpl(jokeService)

    @Test
    fun getRandomJokeEmitsJoke() {
        val joke = Joke(
                faker.idNumber().valid(),
                faker.lorem().sentence())
        whenever(jokeService.getRandomJoke()).thenReturn(Single.just(joke))
        val testObserver = repository.getJoke().test()
        testObserver.assertValue(joke)
    }

}