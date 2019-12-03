package project.readaloud.Objects

import android.media.Image
import android.widget.ImageView

class Page {
    private var text: String? = null
    private var image: ImageView? = null

    constructor(text: String, image: ImageView) {
        this.text = text
        this.image = image

    }
   fun setText(newText: String){
        this.text = newText
    }

    fun setImage(newImage: ImageView){
        this.image = image
    }


}