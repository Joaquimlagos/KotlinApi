package com.api.kotlinspring.controller

import com.api.kotlinspring.controllers.KotlinApiController
import com.api.kotlinspring.enums.BookType
import com.api.kotlinspring.models.AuthorModel
import com.api.kotlinspring.models.BookModel
import com.api.kotlinspring.services.KotlinService
import com.google.gson.Gson
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.hamcrest.Matchers.not
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(KotlinApiController::class)
class KotlinApiControllerTest {

    @MockBean
    private lateinit var kotlinService: KotlinService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun whenPOSTIsCalledThenABookIsCreated() {
        val gson = Gson()
        val author = AuthorModel("pedro", "almeida", 20)
        val book = BookModel("title", 2000,"um livro legal", author, BookType.NATIONAL)
        var bookToJson = gson.toJson(book)

        `when`(kotlinService.create(book)).thenReturn(book)

        mvc.perform(MockMvcRequestBuilders
            .post("/api/book")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookToJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", `is`(book.title)))
            .andExpect(jsonPath("$.year", `is`(book.year)))
            .andExpect(jsonPath("$.description", `is`(book.description)))
            .andExpect(jsonPath("$.bookType", `is`(book.bookType.toString())))
            .andExpect(jsonPath("$.author.name", `is`(book.author.name)))
            .andExpect(jsonPath("$.author.lastName", `is`(book.author.lastName)))
            .andExpect(jsonPath("$.author.age", `is`(book.author.age)))
    }

    @Test
    fun whenGetIsCalledThenABookIsReturned(){
        val id : Int = 0
        val author = AuthorModel("pedro", "almeida", 20)
        val book = BookModel("title", 2000,"um livro legal", author, BookType.NATIONAL)

        `when`(kotlinService.get(id)).thenReturn(book)

        mvc.perform(MockMvcRequestBuilders
            .get("/api/book/" + id)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
    }

    @Test
    fun whenDeleteIsCalledThenAStatusNoContentIsReturned(){
        val id : Int = 0

        Mockito.doNothing().`when`(kotlinService).delete(id)

        mvc.perform(MockMvcRequestBuilders
            .delete("/api/book/" + id)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
    }

    @Test
    fun whenPUTIsCalledTheAUpdateBook(){
        val id : Int = 0
        val gson = Gson()
        val author = AuthorModel("pedro", "almeida", 20)
        val book = BookModel("title", 2000,"um livro legal", author, BookType.NATIONAL)
        val author2 = AuthorModel("pedro", "almeida", 20)
        val book2 = BookModel("title2", 2000,"um livro legal", author2, BookType.NATIONAL)
        var bookToJson = gson.toJson(book2)

        `when`(kotlinService.update(id,book2)).thenReturn(book2)

        mvc.perform(MockMvcRequestBuilders
            .put("/api/book/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookToJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", not(book.title.equals(book2.title))))

    }

}

