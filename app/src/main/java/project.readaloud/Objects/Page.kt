package project.readaloud.Objects

import android.media.Image

class Page {
    var text: String? = null
    var image: Image? = null

    constructor(text:String, image:Image){
        this.text = text
        this.image = image

    }



}