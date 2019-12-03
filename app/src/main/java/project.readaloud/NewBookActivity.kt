package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import project.readaloud.Objects.Book

class NewBookActivity : Activity(){
    internal lateinit var bookTitle : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_book)

        bookTitle = findViewById(R.id.bookTitleEditText)

    }

    fun createBook(view: View) {
        val intent = Intent(
                this@NewBookActivity,
                NewPageActivity::class.java
        )
        Log.e("MOOOP", bookTitle!!.text.toString())
        intent.putExtra("BOOK_TITLE", bookTitle.text.toString())
        intent.putExtra("PAGE_NUMBER", "0")

        startActivity(intent)
    }
}