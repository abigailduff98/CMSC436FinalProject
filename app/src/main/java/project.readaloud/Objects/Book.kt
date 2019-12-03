package project.readaloud.Objects


class Book {
    var title: String? = null
    var pagesInBook: ArrayList<Page>? = null

    constructor(id: String, title:String, pages: ArrayList<Page>){
        this.title = title
        this.pagesInBook = pages
    }


    fun addPage(pageToAdd:Page){
        pagesInBook?.add(pageToAdd)
    }

    fun deletePage(pageToDel:Page){
        pagesInBook?.remove(pageToDel)
    }

}