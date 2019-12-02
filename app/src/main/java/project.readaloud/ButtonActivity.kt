package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class ButtonActivity : Activity() {

    companion object {
        // For stuff if needed
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    // Set an OnClickListener on readButton
    // Called each time the user clicks the Button
    fun clickRead(v: View) {

        val goToListIntent = Intent(
                this@ButtonActivity,
                ListViewActivity::class.java
        )
        startActivity(goToListIntent)

    }

    // Set an OnClickListener on createButton
    // Called each time the user clicks the Button
    fun clickCreate(v: View) {
        val intent = Intent(
                this@ButtonActivity,
                NewBookActivity::class.java
        )
        startActivity(intent)

    }
}