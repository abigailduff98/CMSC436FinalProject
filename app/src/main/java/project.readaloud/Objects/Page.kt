package project.readaloud.Objects

import android.media.Image
import android.widget.ImageView

class Page {
    private var text: String
    private var pageNum: Int
    //private var image: ImageView? = null

    constructor(text: String, pageNum: Int) {
        this.text = text
        this.pageNum = pageNum

    }
   fun setText(newText: String){
        this.text = newText
    }


}