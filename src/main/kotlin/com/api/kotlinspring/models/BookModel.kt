package com.api.kotlinspring.models

import com.api.kotlinspring.enums.BookType

data class BookModel(var title: String?,var year: Int?,var description: String?, var author: AuthorModel, var bookType: BookType)