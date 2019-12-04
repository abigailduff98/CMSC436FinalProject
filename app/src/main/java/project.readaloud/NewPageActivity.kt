package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import project.readaloud.Objects.Book
import project.readaloud.R.layout.new_page
import kotlin.collections.ArrayList


class NewPageActivity : Activity() {
    lateinit var pageLabel : TextView
    lateinit var titleLabel : TextView
    var textBox : EditText? = null
    var bookTitle : String? = null

    private var currentPage : Int = 0
    lateinit var pages : ArrayList<String>
    var prevButton : View? = null
    var nextButton : View? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(new_page)

        pages = ArrayList()
        pageLabel  = findViewById(R.id.pageLabel)
        titleLabel = findViewById(R.id.titleLabel)

        bookTitle = intent.getStringExtra("BOOK_TITLE")

        titleLabel.text = bookTitle
        resetPageLabel()

        textBox = findViewById(R.id.textField)

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

        val ref = FirebaseDatabase.getInstance().getReference("books")
        //generate unique key inside the reference
        val bookId = ref.push().key

        val myBook = Book(bookId.toString(), bookTitle!!, pages)

        ref.child(bookId.toString()).setValue(myBook).addOnCompleteListener{
            Toast.makeText(applicationContext,"Book saved successfully", Toast.LENGTH_LONG).show()
        }
        val intent = Intent(
                this@NewPageActivity,
                ListViewActivity::class.java
        )
        startActivity(intent)
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
}