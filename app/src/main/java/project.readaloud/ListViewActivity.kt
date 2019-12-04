package project.readaloud

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import project.readaloud.Objects.Book

lateinit var bookList: MutableList<Book>
lateinit var ref : DatabaseReference
lateinit var bookID: String

class ListViewActivity : ListActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("books")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                //check if a book exists
                if(p0!!.exists()){
                    bookList.clear()
                    for(b in p0.children){
                        val book = b.getValue(Book::class.java)
                        bookList.add(book!!)
                    }

                    val adapter = BookAdapter(applicationContext, R.layout.list_item, bookList)
                    listAdapter = adapter

                }
            }

        });


        /*listAdapter = ListViewAdapter(
                this,
                R.layout.list_item,
                resources.getStringArray(R.array.colors)
        )*/

        listView.setBackgroundColor(resources.getColor(R.color.divider, null))
        // Enable filtering when the user types in the virtual keyboard
        listView.isTextFilterEnabled = true

        // Set an setOnItemClickListener on the ListView
        listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val goToReaderIntent = Intent(
                    this@ListViewActivity,
                    ReaderActivity::class.java
            )
            intent.putExtra("BOOK_TITLE", view.findViewById<TextView>(R.id.text).text)
            startActivity(goToReaderIntent)

            val textView = view.findViewById<TextView>(R.id.text)
            // Display a Toast message indicting the selected item
            Toast.makeText(
                this@ListViewActivity,
                textView.text, Toast.LENGTH_SHORT
            ).show()

        }
    }
}