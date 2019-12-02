package project.readaloud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class ReaderActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reader_page)
    }

}