package com.api.kotlinspring.service


import com.api.kotlinspring.enums.BookType
import com.api.kotlinspring.models.AuthorModel
import com.api.kotlinspring.models.BookModel
import com.api.kotlinspring.services.KotlinService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class KotlinApiServiceTest {

    @Mock
    private lateinit var kotlinService: KotlinService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
    val id : Int = 0
    val author = AuthorModel("pedro", "almeida", 20)
    val book = BookModel("title", 2000,"um livro legal", author, BookType.NATIONAL)
    val author2 = AuthorModel("pedro", "almeida", 20)
    val book2 = BookModel("title2", 2000,"um livro legal", author2, BookType.NATIONAL)

    @Test
    fun whenMethodCreateBookIsCalledThenShuldReturnABook(){
        Mockito.`when`(kotlinService.create(book)).thenReturn(book)
    }

    @Test
    fun whenMethodGetBookIsCalledThenShuldReturnABook(){
        Mockito.`when`(kotlinService.get(id)).thenReturn(book)
    }

    @Test
    fun whenMethodDeleteIsCalledThenShuldNotReturnABook(){
        Mockito.doNothing().`when`(kotlinService).delete(0)
    }

    @Test
    fun whenMethodUpdateIsCalledThenShuldReturnABookUpdated(){
        Mockito.`when`(kotlinService.update(id, book2)).thenReturn(book2)
        assertThat(book.title, not(book2.title))
    }
}