package com.example.flashbeepshake

import android.annotation.TargetApi
import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        flashlightswitch.setOnClickListener {
            flash()
        }

        beepbutton.setOnClickListener {
            beep()
        }

        shakebutton.setOnClickListener {
            vibrate()
        }
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            print(vibrator.hasVibrator())
        } else {
            vibrator.vibrate(500);
            print(vibrator.hasVibrator())
        }
    }

    private fun beep() {
        val tone = ToneGenerator(AudioManager.STREAM_RING, 200)
        tone.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 500)
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun flash() {
        val camera = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val id = camera.cameraIdList[0]
        val flashbutton = findViewById<Switch>(R.id.flashlightswitch)
        if (flashbutton.isChecked) {
            camera.setTorchMode(id, true)
        } else
            camera.setTorchMode(id, false)
    }
}

    //override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    menuInflater.inflate(R.menu.menu_main, menu)
    //    return true
    //}

    //override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     //   return when (item.itemId) {
    //        R.id.action_settings -> true
    //        else -> super.onOptionsItemSelected(item)
     //   }
    //}

