package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import project.readaloud.Objects.Book
import java.util.ArrayList

private const val MYTAG = "Book create"

class NewBookActivity : Activity(){
    internal lateinit var bookTitle : EditText
    lateinit var buttonSave: Button

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_book)
        bookTitle = findViewById(R.id.bookTitleEditText)
        buttonSave = findViewById(R.id.submitBookTitleButton)

        buttonSave.setOnClickListener{
            createBook()
        }

    }

    private fun createBook() {
        val title = bookTitle.text.toString().trim()
        if(title.isEmpty()){
            bookTitle.error = "Please enter a title"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("books")
        //generate unique key inside the reference
        val bookId = ref.push().key
        val myBook = Book(bookId.toString(), title, ArrayList())

        ref.child(bookId.toString()).setValue(myBook).addOnCompleteListener{
            Toast.makeText(applicationContext,"Book saved successfully", Toast.LENGTH_LONG).show()
        }

        val intent = Intent(
                this@NewBookActivity,
                NewPageActivity::class.java
        )
        intent.putExtra("BOOK_ID", bookId.toString())
        intent.putExtra("BOOK_TITLE", title)

        startActivity(intent)
    }
}