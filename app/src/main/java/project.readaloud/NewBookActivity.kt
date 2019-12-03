package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import project.readaloud.Objects.Book
import project.readaloud.Objects.Page

class NewBookActivity : Activity(){
    var bookTitle : EditText? = null
    //var newBook : Book? = null

    private var mAuth: FirebaseAuth? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mAuth = FirebaseAuth.getInstance()
        bookTitle = findViewById(R.id.bookTitleEditText)
        setContentView(R.layout.new_book)
        //newBook = Book(bookTitle.toString(), ArrayList<Page>())
    }

    fun createBook(view: View) {
        val intent = Intent(
                this@NewBookActivity,
                NewPageActivity::class.java
        )
        intent.putExtra("title", bookTitle.toString())
        startActivity(intent)
    }
}