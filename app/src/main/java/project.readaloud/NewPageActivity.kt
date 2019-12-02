package project.readaloud

import android.app.Activity
import android.os.Bundle

class NewPageActivity : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_page)
    }
}