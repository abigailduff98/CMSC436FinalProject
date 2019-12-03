package project.readaloud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import project.readaloud.Objects.Book

class BookAdapter(val mCtx: Context, val layoutResID: Int, val bookList: List<Book>)
    : ArrayAdapter<Book>(mCtx, layoutResID, bookList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(layoutResID, null)

        val textViewName = view.findViewById<TextView>(R.id.text)
        val book = bookList[position]

        textViewName.text = book.title
        return view
    }

}