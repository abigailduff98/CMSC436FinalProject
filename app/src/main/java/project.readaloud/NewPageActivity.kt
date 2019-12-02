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

    fun prevPage(v: View) {

        val int = Intent(
                this@NewPageActivity,
                NewPageActivity::class.java
        )
        startActivity(int)

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