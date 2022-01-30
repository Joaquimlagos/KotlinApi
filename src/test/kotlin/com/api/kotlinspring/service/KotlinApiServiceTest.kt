package com.api.kotlinspring.service


import com.api.kotlinspring.enums.BookType
import com.api.kotlinspring.models.AuthorModel
import com.api.kotlinspring.models.BookModel
import com.api.kotlinspring.services.KotlinService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.util.Assert
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


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
    val titleFormated = "o nome do livro é title"

    @Test
    fun whenMethodCreateBookIsCalledThenShuldReturnABook(){
        Mockito.`when`(kotlinService.create(book)).thenReturn(book)
        var result :BookModel = kotlinService.create(book)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(book,result)
    }

    @Test
    fun whenMethodGetBookIsCalledThenShuldReturnABook(){
        Mockito.`when`(kotlinService.get(id)).thenReturn(book)

        var result :BookModel = kotlinService.get(id)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(book,result)
    }

    @Test
    fun whenMethodDeleteIsCalledThenShuldNotReturnABook(){
        kotlinService.delete(id)
        verify(kotlinService, times(1)).delete(id)
    }

    @Test
    fun whenMethodUpdateIsCalledThenShuldReturnABook(){
        Mockito.`when`(kotlinService.update(id, book2)).thenReturn(book2)

        var result : BookModel = kotlinService.update(id,book2)

        Assertions.assertNotNull(result)
    }
}