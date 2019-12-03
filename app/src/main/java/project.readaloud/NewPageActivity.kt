package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import project.readaloud.R.layout.new_page

class NewPageActivity : Activity() {
    var text : EditText? = null
    private lateinit var saveButton : Button
    private lateinit var nextButton : Button



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        text = findViewById(R.id.storyText)
        saveButton = findViewById(R.id.saveButton)
        nextButton = findViewById(R.id.nextPageButton)

        saveButton.setOnClickListener(){

        }
        setContentView(new_page)
    }

    //save previous content and be able to show it
    //first page shouldn't have prev
    //create page
    //save instance state
    //keep a stack of activities
    //save button (finish)

    //save entire book to database




    fun save() {
        val intent = Intent(
                this@NewPageActivity,
                CreateBookActivity::class.java
        )
        startActivity(intent)
    }

    // Set an OnClickListener on createButton
    // Called each time the user clicks the Button
    //save to external
    fun nextPage() {
        val intent = Intent(
                this@NewPageActivity,
                NewPageActivity::class.java
        )
        startActivity(intent)

    }
}