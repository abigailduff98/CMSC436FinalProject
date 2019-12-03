package project.readaloud

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.ImageView
import android.widget.Toast
import java.util.Locale




class ReaderActivity : Activity(), OnInitListener {

    //TTS object
    private var myTTS: TextToSpeech? = null
    //status check code
    private val MY_DATA_CHECK_CODE = 0


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reader_page)

        //get a reference to the button element listed in the XML layout
        val speakButton = findViewById<Button>(R.id.playButton)
        //listen for clicks
        speakButton.setOnClickListener {

            //get the text entered
            val enteredText = findViewById<TextView>(R.id.storyText)
            val words = enteredText.text.toString()
            speakWords(words)
        }

        //get a reference to the button element listed in the XML layout
        val stopButton = findViewById<Button>(R.id.stopButton)
        //listen for clicks
        stopButton.setOnClickListener {
            if (myTTS != null) {
                myTTS!!.stop()
                findViewById<ImageView>(R.id.imageView1).setImageResource(R.drawable.mrwhite)
            }
        }

        //check for TTS data
        val checkTTSIntent = Intent()
        checkTTSIntent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE)
    }

    private fun speakWords(speech: String) {

        val params = Bundle()
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "")
        //speak straight away
        myTTS!!.speak(speech, TextToSpeech.QUEUE_FLUSH, params, "UniqueID")
    }

    //act on result of TTS data check
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = TextToSpeech(this, this)
            } else {
                //no data - install it now
                val installTTSIntent = Intent()
                installTTSIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                startActivity(installTTSIntent)
            }
        }
    }

    //setup TTS
    override fun onInit(initStatus: Int) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if (myTTS!!.isLanguageAvailable(Locale.US) === TextToSpeech.LANG_AVAILABLE)
                myTTS!!.setLanguage(Locale.US)
                myTTS!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {

                    override fun onDone(utteranceId: String?) {
                       findViewById<ImageView>(R.id.imageView1).setImageResource(R.drawable.mrwhite)
                    }

                    override fun onError(utteranceId: String?) {
                        //do whatever you want if TTS makes an error.
                    }

                    override fun onStart(utteranceId: String?) {
                        findViewById<ImageView>(R.id.imageView1).setImageResource(R.drawable.mrwhitemouth)
                    }
                })
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show()
        }
    }

}

