package com.api.kotlinspring.controllers

import com.api.kotlinspring.models.BookModel
import com.api.kotlinspring.services.KotlinService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book")
class KotlinApiController(val kotlinService: KotlinService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody body: BookModel): BookModel {
        val bookResponse = kotlinService.create(body)
        return bookResponse
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Int): BookModel {
        val bookResponse = kotlinService.get(id)
        return bookResponse
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Int, @RequestBody body:BookModel): BookModel {
        val bookResponse = kotlinService.update(id,body)
        return bookResponse
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int){
        kotlinService.delete(id)
    }

    @GetMapping("/get")
    fun getAllBooks():List<BookModel>{
        var allBooks = kotlinService.list()
        return allBooks
    }
}