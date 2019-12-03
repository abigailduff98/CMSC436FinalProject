package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class NewPageActivity : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_page)
    }

    //save previous content and be able to show it
    //first page shouldn't have prev
    //create page
    //save instance state
    //keep a stack of activities
    //save button (finish)
    fun prevPage(v: View) {

        val intent = Intent(
                this@NewPageActivity,
                NewPageActivity::class.java
        )
        startActivity(intent)

    }

    fun savePage(v: View) {
        val intent = Intent(
                this@NewPageActivity,
                NewPageActivity::class.java
        )
        startActivity(intent)
    }

    // Set an OnClickListener on createButton
    // Called each time the user clicks the Button
    fun nextPage(v: View) {
        val intent = Intent(
                this@NewPageActivity,
                NewPageActivity::class.java
        )
        startActivity(intent)

    }
}