package project.readaloud.Objects


class Book {
    var id: String? = null
    var title: String? = null
    var pagesInBook: ArrayList<String>? = null

    constructor(id: String, title: String, pages: ArrayList<String>){
        this.id = id
        this.title = title
        this.pagesInBook = pages
    }
    constructor() : this("","", ArrayList())
}