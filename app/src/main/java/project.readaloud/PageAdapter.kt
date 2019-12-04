package project.readaloud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PageAdapter(val mCtx: Context, val layoutResID: Int, val pageList: List<String>)
    : ArrayAdapter<String>(mCtx, layoutResID, pageList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(layoutResID, null)

        val textViewName = view.findViewById<TextView>(R.id.storyText)
        val page = pageList[position]

        textViewName.text = page
        return view
    }

}