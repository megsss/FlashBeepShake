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
import android.util.Log
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
            Log.v("vibrating:", vibrator.hasVibrator().toString())
        } else {
            vibrator.vibrate(500);
            Log.v("vibrating:", vibrator.hasVibrator().toString())
        }
    }

    private fun beep() {
        val tone = ToneGenerator(AudioManager.STREAM_RING, 200)
        tone.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 500)
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun flash() {
        val flashbutton = findViewById<Switch>(R.id.flashlightswitch)
        Log.v("flash on:", flashbutton.isChecked().toString())

        val camera = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val id = camera.cameraIdList[0]

        if (flashbutton.isChecked) {
            camera.setTorchMode(id, true)
        } else
            camera.setTorchMode(id, false)
    }
}



