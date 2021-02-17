package com.example.pitzersoundtester

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var music: MediaPlayer
    private lateinit var musicSwitch: Switch
    private lateinit var slider: SeekBar

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  initialize MediaPlayer object for music
        //  and start it (looping!) at initial .75 volume
        music = MediaPlayer.create(applicationContext, R.raw.backgroundtunes)
        music.start()
        music.setVolume((75/100).toFloat(), (75/100).toFloat())
        music.isLooping = true

        //  define the behavior of the BackgroundMusic Switch
        musicSwitch = findViewById(R.id.musicSwitch)
        musicSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                music.pause()
            } else {
                music.start()
            }
        }

        //  define the behavior of the volume SeekSwitch
        slider = findViewById(R.id.volumeBar)
        slider.setOnSeekBarChangeListener(this)
        slider.setProgress(75, true)

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        Log.i("Main", "Background music adjusted to: " + progress)

        music.setVolume(progress.toFloat()/100, progress.toFloat()/100)

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    //  helper method that plays Tada sound when Tada button is clicked
    fun playTada(view: View) {

        Log.i("myApp", "user pressed " + (view as Button).text)

        val tada: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.tada)
        tada.start()

    }

    //  helper method that plays Elephant sound and displays toast message
    fun playElephant(view: View) {

        Log.i("myApp", "user pressed " + (view as Button).text)

        val tada: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.elephant)
        tada.start()

        Toast.makeText(applicationContext, "Trumpet!", Toast.LENGTH_SHORT).show()

    }

    //  helper method that logs the action of the Background Music being clicked
    fun logAction(view: View) {
        Log.i("myApp", "user pressed " + (view as Switch).text)
    }

}