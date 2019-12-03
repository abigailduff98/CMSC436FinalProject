package project.readaloud
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri

import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import project.readaloud.Objects.Page
import project.readaloud.Objects.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.new_book.*
import kotlinx.android.synthetic.main.new_book.view.*
import kotlinx.android.synthetic.main.new_book.view.bookTitleEditText
import kotlinx.android.synthetic.main.new_page.*
import java.net.URL
import java.util.*

private const val MYTAG = "Book create"

class CreateBookActivity : Activity() {

    private lateinit var titleTV: EditText
    private lateinit var saveButtonView: Button


    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reader_page)

        Log.i(MYTAG, "Starting")

        mAuth = FirebaseAuth.getInstance()
        titleTV = findViewById(R.id.bookTitleEditText)

        saveButtonView.setOnClickListener { createBook() }

    }


    private fun createBook() {
        Log.i(MYTAG, "Now We create the book...")
        //When the user clicks register
        val title: String = titleTV.text.toString()


        if (TextUtils.isEmpty(title)) {
            Toast.makeText(applicationContext, "Please enter the title...", Toast.LENGTH_LONG).show()
            return
        }
        saveBookToFireBaseDataBase()
        //If all the slots are filled out. We authenticate
    }




    private fun saveBookToFireBaseDataBase() {
        Log.i(MYTAG, "Let's Save The user to Firebase")
        val myRef = FirebaseDatabase.getInstance().getReference("/books")

        //create book
        val myBook = Book(R.id.bookTitleEditText.toString())
        val imageView = findViewById<ImageView>(R.id.canvasView)


        //add an item to the users items just to be sure. it works
        val firstPage = Page("Once upon a time...", imageView)
        myBook.addPage(firstPage)

        //add the user to the Databse
        val x = myRef.setValue(myBook)

        x.addOnSuccessListener {
            Log.i(MYTAG, "We saved the Book")
        }
    }



}