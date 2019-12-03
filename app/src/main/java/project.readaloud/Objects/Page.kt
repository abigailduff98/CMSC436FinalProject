package project.readaloud.Objects

import android.media.Image
import android.widget.ImageView

class Page {
    private var text: String? = null
    //private var image: ImageView? = null

    constructor(text: String) {
        this.text = text

    }
   fun setText(newText: String){
        this.text = newText
    }






}