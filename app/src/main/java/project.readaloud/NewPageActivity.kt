package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View

import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import project.readaloud.Objects.Book
import project.readaloud.R.layout.new_page
import kotlin.collections.ArrayList


class NewPageActivity : Activity() {
    lateinit var pageLabel : TextView
    lateinit var titleLabel : TextView
    var textBox : EditText? = null
    var bookTitle : String? = null
    var bookId : String? = null
    var ref : DatabaseReference? = null

    private var currentPage : Int = 0
    lateinit var pages : ArrayList<String>
    var prevButton : View? = null
    var nextButton : View? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(new_page)

        ref = FirebaseDatabase.getInstance().getReference("books")
        pageLabel  = findViewById(R.id.pageLabel)
        titleLabel = findViewById(R.id.titleLabel)

        bookTitle = intent.getStringExtra("BOOK_TITLE")
        bookId = intent.getStringExtra("BOOK_ID")
        pages = ArrayList()
        textBox = findViewById(R.id.textField)

        if(bookId == null){
            bookId = ref?.push()!!.key
        } else {
            ref?.child(bookId!!)?.child("pagesInBook")?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // getting authors only for the Current User
                    for (snapshot in dataSnapshot.children) {
                        pages.add(snapshot.value.toString())
                    }
                    textBox?.setText(pages[0])
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }

        titleLabel.text = bookTitle
        resetPageLabel()


        prevButton = findViewById(R.id.prevPageButton)
        nextButton = findViewById(R.id.nextPageButton)

        prevButton?.visibility = View.INVISIBLE
    }

    //save previous content and be able to show it
    //first page shouldn't have prev
    //create page
    //save instance state
    //keep a stack of activities
    //save button (finish)

    //save entire book to database


    fun save(view: View) {
    //will go to list view activity
        //story page?
        val newPage = textBox?.text.toString()
        if(newPage != "") {
            if (currentPage >= pages.size) {
                pages.add(currentPage, newPage)
            } else {
                pages[currentPage] = newPage
            }
        }

        val myBook = Book(bookId.toString(), bookTitle!!, pages)

        ref?.child(bookId.toString())?.setValue(myBook)?.addOnCompleteListener{
            Toast.makeText(applicationContext,"Book saved successfully", Toast.LENGTH_LONG).show()
        }
        finish()
    }

    // Set an OnClickListener on createButton
    // Called each time the user clicks the Button
    //save to external
    fun nextPage(view: View) {
        val newPage = textBox?.text.toString()

        if(currentPage >= pages.size && newPage.isEmpty()){
            textBox?.error = "Please enter some text for this page"
            return
        }

        if(currentPage >= pages.size){
            pages.add(currentPage, newPage)
        } else {
            pages[currentPage] = newPage
        }

        currentPage += 1
        resetPageLabel()
        prevButton?.visibility = View.VISIBLE

        if(currentPage >= pages.size){
            textBox?.setText("")
        } else {
            textBox?.setText(pages[currentPage])
        }
    }
    fun prevPage(view: View) {
        if(currentPage == 1) {
            prevButton?.visibility = View.INVISIBLE
        }

        val newPage = textBox?.text.toString()

        if(newPage != "") {
            if (currentPage >= pages.size) {
                pages.add(currentPage, newPage)
            } else {
                pages[currentPage] = newPage
            }
        }

        currentPage-= 1
        resetPageLabel()

        textBox?.setText(pages[currentPage])
    }

    fun resetPageLabel(){
        pageLabel.text = "Page " + (currentPage + 1).toString()
    }

    fun deleteButton(view: View) {
        if(currentPage == pages.size - 1 && pages.size == 1){
            textBox?.setText("")
        } else if (currentPage > pages.size - 1){
            Log.e("MOOOP", pages.toString())
            currentPage--
            textBox?.setText(pages[currentPage])
            resetPageLabel()
        } else if (currentPage == pages.size - 1){
            pages.removeAt(currentPage)
            currentPage--
            textBox?.setText(pages[currentPage])
            resetPageLabel()
        }
        else {
            pages.removeAt(currentPage)
            textBox?.setText(pages[currentPage])
        }
    }
}