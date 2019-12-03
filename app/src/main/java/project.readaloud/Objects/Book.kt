package project.readaloud.Objects

class Book {
    var title: String? = null
    var pagesInBook: ArrayList<Page>? = null

    constructor(title:String){
        this.title = title

        this.pagesInBook = ArrayList<Page>()
    }


    fun addPage(pageToAdd:Page){
        pagesInBook?.add(pageToAdd)
    }

    fun deletePage(pageToDel:Page){
        pagesInBook?.remove(pageToDel)
    }

}