package project.readaloud
import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import project.readaloud.Objects.Page
import project.readaloud.Objects.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.storage.FirebaseStorage
import java.net.URL
import java.util.*

private const val TAG = "Book Creation"

class CreateBookActivity : Activity() {
    //private const val MYTAG = "myRegistrationAct"

    //class RegistrationActivity : AppCompatActivity() {

        private lateinit var bookTitleView: EditText
        //private lateinit var bookAuthorView: EditText
        private lateinit var saveButtnView: Button
        private var mAuth: FirebaseAuth? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.new_book)


            //Log.i(MYTAG, "Welcome to Registration")
            //initializeUI()

            mAuth = FirebaseAuth.getInstance()

            saveButtnView.setOnClickListener { saveBook() }
            //fakeregisterButtnView.setOnClickListener { fakeRegisterNewUser() }
            //select_photo_button_id.setOnClickListener { selectPhoto() }
        }




        private fun saveBook() {
            Log.i(TAG, "Save the book...")
            //When the user clicks save
            val bookTitle: String = bookTitleView.text.toString()
            //val bookAuthor: String = bookAuthorView.text.toString()
            //need to add how to saave an image

            if (TextUtils.isEmpty(bookTitle)) {
                Toast.makeText(applicationContext, "Please enter the Title...", Toast.LENGTH_LONG).show()
                return
            }/* else if (TextUtils.isEmpty(bookAuthor)) {
                Toast.makeText(applicationContext, "Please enter your name...", Toast.LENGTH_LONG).show()
                return
            }*/

            //If all the slots are filled out. We authenticate


        }



        private fun saveBookToFireBaseDataBase(myBookTitle: String) {
            Log.i(TAG, "Save book to Firebase")
            val uid = FirebaseAuth.getInstance().uid ?: ""
            val myUserRef = FirebaseDatabase.getInstance().getReference("/users/$uid")


            val title = bookTitleView.text.toString()

            //TODO need to instantiate book
           // val myBook = Book(title, pages)
            //function Book


            // TODO create page, add to book object
            //val firstPage = Pages("")
            //myBook.addPage(firstPage)

            //TODO add the user to the Databse
            //val x = myUserRef.setValue(myBook)

            /*x.addOnSuccessListener {
                Log.i(TAG, "Saved the book")
            }*/
        }



        private fun initializeUI() {
            Log.i(TAG, "Initialize UI")
            bookTitleView = findViewById(R.id.bookTitleEditText)
            //need to add this button
            //saveButtnView = findViewById(R.id.saveButtonView)


        }
    }
