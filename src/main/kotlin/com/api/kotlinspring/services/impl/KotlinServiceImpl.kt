package com.api.kotlinspring.services.impl

import com.api.kotlinspring.exeptions.NotFoundException
import com.api.kotlinspring.models.BookModel
import com.api.kotlinspring.services.KotlinService
import org.springframework.stereotype.Service

@Service
class KotlinServiceImpl(): KotlinService {
    val bookList: ArrayList<BookModel> = ArrayList()

    override  fun create(bookModel: BookModel): BookModel {
        val titleFormated = titleTextFormated(bookModel.title)

        val book = BookModel(
            title = titleFormated,
            year = bookModel.year,
            description = bookModel.description,
            author = bookModel.author,
            bookType = bookModel.bookType
        )
        bookList.add(book)

        return book
    }

    override fun get(id: Int): BookModel {
        try {
            return bookList.get(id)
        } catch (e: IndexOutOfBoundsException){
            throw NotFoundException("id $id não encontrado")
        }
    }

    override fun update(id: Int, bookModel: BookModel): BookModel {

       val book = bookList.get(id)
       val titleFormated = titleTextFormated(bookModel.title)

        book.apply {
            title = titleFormated
            year = bookModel.year
            description = bookModel.description
            author = bookModel.author
            bookType = bookModel.bookType
        }
        return book
    }

    override fun delete(id: Int){
        bookList.removeAt(id)
    }

    override fun list(): List<BookModel> {
        return bookList
    }

    private fun titleTextFormated(title :String?): String{
        return "o nome do livro é de $title"
    }
}