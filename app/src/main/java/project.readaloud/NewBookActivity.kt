package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText

class NewBookActivity : Activity(){
    var bookTitle : EditText? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookTitle = findViewById(R.id.bookTitleEditText)
        setContentView(R.layout.new_book)
    }

    fun createBook(view: View) {
        val intent = Intent(
                this@NewBookActivity,
                NewPageActivity::class.java
        )
        startActivity(intent)
    }
}