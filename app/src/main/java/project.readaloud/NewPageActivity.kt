package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import project.readaloud.R.layout.new_page

class NewPageActivity : Activity() {
    lateinit var titleLabel : TextView

    var text : EditText? = null
    lateinit var bookTitle : String
    private var pageNumber : Int? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(new_page)

        titleLabel  = findViewById(R.id.titleLabel)

        bookTitle = intent.getStringExtra("BOOK_TITLE")
        pageNumber = intent.getStringExtra("PAGE_NUMBER").toInt()
        Log.e("ldsa", titleLabel.toString())
        titleLabel.text = bookTitle + ": Page " + pageNumber.toString()

        text = findViewById(R.id.storyText)

    }

    //save previous content and be able to show it
    //first page shouldn't have prev
    //create page
    //save instance state
    //keep a stack of activities
    //save button (finish)

    //save entire book to database




    fun save(v: View) {
        val intent = Intent(
                this@NewPageActivity,
                CreateBookActivity::class.java
        )
        startActivity(intent)
    }

    // Set an OnClickListener on createButton
    // Called each time the user clicks the Button
    //save to external
    fun nextPage(v: View) {
        val intent = Intent(
                this@NewPageActivity,
                NewPageActivity::class.java
        )
        intent.putExtra("BOOK_TITLE", bookTitle)
        intent.putExtra("PAGE_NUMBER", pageNumber?.plus(1).toString())
        startActivity(intent)

    }
}