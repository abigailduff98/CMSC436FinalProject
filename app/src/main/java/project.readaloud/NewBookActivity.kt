package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class NewBookActivity : Activity(){


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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