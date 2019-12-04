package project.readaloud

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*
import project.readaloud.Objects.Book
import java.util.Locale
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




lateinit var pageList: ArrayList<String>
//lateinit var ref : DatabaseReference
lateinit var bookId: String

class ReaderActivity : Activity(), OnInitListener {

    //TTS object
    private var myTTS: TextToSpeech? = null
    //status check code
    private val MY_DATA_CHECK_CODE = 0
    private var currIndex = 0
    var enteredText : TextView? = null
    var lastPageRead = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reader_page)
        enteredText = findViewById<TextView>(R.id.storyText)
        val speakButton = findViewById<Button>(R.id.playButton)

        pageList = ArrayList()
        bookId = intent.getStringExtra("BOOK_ID")!!

        val ref = FirebaseDatabase.getInstance().getReference("books")

        ref.child(bookId).child("pagesInBook").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // getting authors only for the Current User
                for (snapshot in dataSnapshot.children) {
                    pageList.add(snapshot.value.toString())
                }
                enteredText?.text = pageList[currIndex]

            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        speakButton.setOnClickListener {

            //get the text entered

            val words = enteredText?.text.toString()
            speakWords(words)
        }

        val stopButton = findViewById<Button>(R.id.stopButton)
        stopButton.setOnClickListener {
            if (myTTS != null) {
                myTTS!!.stop()
                findViewById<ImageView>(R.id.imageView1).setImageResource(R.drawable.mrwhite)
            }
        }

        val checkTTSIntent = Intent()
        checkTTSIntent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE)
    }

    private fun speakWords(speech: String) {
        val params = Bundle()
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "")
        myTTS!!.speak(speech, TextToSpeech.QUEUE_FLUSH, params, "UniqueID")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = TextToSpeech(this, this)
            } else {
                val installTTSIntent = Intent()
                installTTSIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                startActivity(installTTSIntent)
            }
        }
    }

    override fun onInit(initStatus: Int) {

        if (initStatus == TextToSpeech.SUCCESS) {
            if (myTTS!!.isLanguageAvailable(Locale.US) === TextToSpeech.LANG_AVAILABLE)
                myTTS!!.setLanguage(Locale.US)
                myTTS!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {

                    override fun onDone(utteranceId: String?) {
                       findViewById<ImageView>(R.id.imageView1).setImageResource(R.drawable.mrwhite)
                        next()
                        val words = enteredText?.text.toString()
                        if(currIndex < pageList.size && !lastPageRead) {
                            speakWords(words)
                            if(currIndex == pageList.size - 1){
                                lastPageRead = true
                            }
                        }
                    }

                    override fun onError(utteranceId: String?) {
                        //do whatever
                    }

                    override fun onStart(utteranceId: String?) {
                        findViewById<ImageView>(R.id.imageView1).setImageResource(R.drawable.mrwhitemouth)
                    }
                })
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show()
        }
    }
    fun next() {
        if(currIndex < pageList.size - 1) {
            currIndex++
            enteredText?.text = pageList[currIndex]
        }
    }
    fun nexPage(view: View) {
        next()
    }

    fun prePage(view: View) {
        if(currIndex > 0) {
            lastPageRead = false
            currIndex--
            enteredText?.text = pageList[currIndex]
        }
    }

}

