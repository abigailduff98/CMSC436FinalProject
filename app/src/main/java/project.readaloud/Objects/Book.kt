package project.readaloud.Objects


class Book {
    var title: String? = null
    var pagesInBook: ArrayList<String>? = null

    constructor(title: String, pages: ArrayList<String>){
        this.title = title
        this.pagesInBook = pages
    }
//    constructor() : this("","", ArrayList()){
//
//    }
//
//
//    fun addPage(pageToAdd:Page){
//        pagesInBook?.add(pageToAdd)
//    }
//
//    fun deletePage(pageToDel:Page){
//        pagesInBook?.remove(pageToDel)
//    }

}