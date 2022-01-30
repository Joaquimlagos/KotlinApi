package com.api.kotlinspring.services

import com.api.kotlinspring.models.BookModel

interface KotlinService {

    fun create(bookModel: BookModel): BookModel

    fun get(id: Int): BookModel

    fun update(id: Int, bookModel: BookModel): BookModel

    fun delete(id: Int)

    fun list(): List<BookModel>

}