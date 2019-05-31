package com.dabinu.apps.chesstimer.fragments


import android.annotation.TargetApi
import android.app.Activity
import android.app.FragmentTransaction
import android.content.Context
import android.content.SharedPreferences
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Vibrator
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.dabinu.apps.chesstimer.R
import kotlinx.android.synthetic.main.fragment_timer.*


/**
 * A simple [Fragment] subclass.
 */

class TimerFragment : android.app.Fragment(), View.OnClickListener, Runnable {



    lateinit var gameDetails: SharedPreferences
    lateinit var appDetails: SharedPreferences
    internal var shouldRing: Boolean = false
    internal var shouldVibrate: Boolean = false
    var active_color: Int = 0
    var inactive_color: Int = 0
    var background_color: Int = 0
    internal var default_color: Int = 0
    var duration: Long = 0
    internal var delay: Long = 0
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var background: RelativeLayout
    internal var countDownTimer: CountDownTimer? = null
    internal lateinit var vibrator: Vibrator
    private val _handler = Handler()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {

        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        setFullscreen(activity)
        if (Build.VERSION.SDK_INT > 10) {
            registerSystemUiVisibility()
        }


        loadGameDetails()
        loadAllSettings()
        init()


        return view
    }


    fun loadGameDetails() {
        gameDetails = activity.getSharedPreferences("GAME", Context.MODE_PRIVATE)

        duration = java.lang.Long.parseLong(gameDetails.getString("duration", "5")!!) * 60 * 1000
        delay = java.lang.Long.parseLong(gameDetails.getString("delay", "0")!!) * 1000
    }


    fun loadAllSettings() {
        appDetails = activity.getSharedPreferences("DETAILS", Context.MODE_PRIVATE)

        shouldRing = appDetails.getString("ring", "true") == "true"
        shouldVibrate = appDetails.getString("vibrate", "true") == "true"

        active_color = getColorFromString(appDetails.getString("active_color", "Green"))
        inactive_color = getColorFromString(appDetails.getString("inactive_color", "Red"))
        default_color = getColorFromString(appDetails.getString("default_color", "Gray"))
        background_color = getColorFromString(appDetails.getString("background_color", "Deep-Gray"))

    }


    fun init() {

        background.setBackgroundColor(resources.getColor(background_color))

        playerA.setCardBackgroundColor(resources.getColor(default_color))
        playerB.setCardBackgroundColor(resources.getColor(default_color))

        timerA.text = convertLongToString(duration / 1000)
        timerB.text = convertLongToString(duration / 1000)

        pause.setOnClickListener(this)
        reset.setOnClickListener(this)
        settings.setOnClickListener(this)

        vibrator = activity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    }


    fun getColorFromString(colorName: String?): Int {
        when (colorName) {
            "Red" -> return R.color.red
            "Pink" -> return R.color.pink
            "Orange" -> return R.color.orange
            "Purple" -> return R.color.purple
            "Yellow" -> return R.color.yellow
            "Gray" -> return R.color.gray
            "Green" -> return R.color.green
            "Black" -> return R.color.black
            "Blue" -> return R.color.blue
            "Deep-Gray" -> return R.color.deep_gray
        }

        return android.R.color.holo_purple
    }


    override fun onClick(view: View) {
        if (view.id == R.id.settings) {
            goToFragement(SettingsFragment())
        } else if (view.id == R.id.reset) {
            goToFragement(TimerFragment())
        } else if (view.id == R.id.playerA) {
            if (delay == 0L) {
                playerToggler(playerA)
            } else {
                initialToggler(playerA)
            }
        } else if (view.id == R.id.playerB) {
            if (delay == 0L) {
                playerToggler(playerB)
            } else {
                initialToggler(playerB)
            }
        } else if (view.id == R.id.pause) {

            if (countDownTimer != null) {
                countDownTimer!!.cancel()
            }

            Toast.makeText(activity.applicationContext, "Paused", Toast.LENGTH_SHORT).show()

            playerA.setCardBackgroundColor(resources.getColor(default_color))
            playerB.setCardBackgroundColor(resources.getColor(default_color))

            settings.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            pause.visibility = View.GONE
        }
    }


    fun goToFragement(fragment: android.app.Fragment) {
        fragmentTransaction = activity.fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }


    fun playerToggler(player: CardView) {
        val nextPlayer = if (player.id == R.id.playerA) playerB else playerA
        val nextTextView = if (nextPlayer.id == R.id.playerA) timerA else timerB
        val thisMoveView = if (nextPlayer.id == R.id.playerA) movesB else movesA

        reset.visibility = View.GONE
        settings.visibility = View.GONE
        pause.visibility = View.VISIBLE

        thisMoveView.setText(String.format("Moves: %d", Integer.parseInt(thisMoveView.text.toString().trim { it <= ' ' }.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]) + 1))

        player.setCardBackgroundColor(resources.getColor(inactive_color))
        nextPlayer.setCardBackgroundColor(resources.getColor(active_color))

        countDownTimer = object : CountDownTimer(convertStringToLong(nextTextView.text.toString().trim { it <= ' ' }) * 1000, 1000) {
            override fun onTick(l: Long) {
                nextTextView.text = convertLongToString(l / 1000)
                nextPlayer.setOnClickListener {
                    countDownTimer!!.cancel()
                    if (delay == 0L) {
                        playerToggler(nextPlayer)
                    } else {
                        initialToggler(nextPlayer)
                    }
                }
                player.setOnClickListener(null)

            }

            override fun onFinish() {
                nextTextView.text = "Time up!!!"

                reset.visibility = View.VISIBLE
                settings.visibility = View.VISIBLE
                pause.visibility = View.GONE

                playerA.setCardBackgroundColor(resources.getColor(default_color))
                playerB.setCardBackgroundColor(resources.getColor(default_color))

                playerA.setOnClickListener(null)
                playerB.setOnClickListener(null)

                if (shouldVibrate) {
                    vibrator.vibrate(2000)
                }
                if (shouldRing) {
                    try {
                        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        val r = RingtoneManager.getRingtone(activity.applicationContext, notification)
                        r.play()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

            }

        }.start()
    }


    fun initialToggler(player: CardView) {
        val nextPlayer = if (player.id == R.id.playerA) playerB else playerA
        val nextTextView = if (nextPlayer.id == R.id.playerA) timerA else timerB
        val thisMoveView = if (nextPlayer.id == R.id.playerA) movesB else movesA

        reset.visibility = View.GONE
        settings.visibility = View.GONE
        pause.visibility = View.VISIBLE

        thisMoveView.setText(String.format("Moves: %d", Integer.parseInt(thisMoveView.text.toString().trim { it <= ' ' }.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]) + 1))

        player.setCardBackgroundColor(resources.getColor(inactive_color))
        nextPlayer.setCardBackgroundColor(resources.getColor(active_color))

        countDownTimer = object : CountDownTimer(delay, 1000) {
            override fun onTick(l: Long) {
                nextPlayer.setOnClickListener {
                    countDownTimer!!.cancel()
                    if (delay == 0L) {
                        playerToggler(nextPlayer)
                    } else {
                        initialToggler(nextPlayer)
                    }
                }
                player.setOnClickListener(null)
            }

            override fun onFinish() {
                playerToggler(player)
            }

        }.start()
    }


    fun convertLongToString(number: Long): String? {

        var timeString: String? = null

        if (number >= 600) {
            timeString = java.lang.Long.toString(number / 60)
        } else if (number < 60) {
            timeString = "00"
        } else if (number >= 60 && number < 600) {
            timeString = "0" + java.lang.Long.toString(number / 60)
        }

        val secondHalfOfTime: String

        if (number % 60 < 10) {
            secondHalfOfTime = "0" + java.lang.Long.toString(number % 60)
        } else {
            secondHalfOfTime = java.lang.Long.toString(number % 60)
        }

        timeString += String.format(":%s", secondHalfOfTime)

        return timeString
    }


    override fun onPause() {
        if (countDownTimer != null) {
            playerA.setCardBackgroundColor(resources.getColor(default_color))
            playerB.setCardBackgroundColor(resources.getColor(default_color))
            countDownTimer!!.cancel()
        }

        super.onPause()
    }


    fun setFullscreen(activity: Activity) {
        if (Build.VERSION.SDK_INT > 10) {
            var flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_FULLSCREEN

            if (isImmersiveAvailable) {
                flags = flags or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }

            activity.window.decorView.systemUiVisibility = flags
        } else {
            activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun registerSystemUiVisibility() {
        val decorView = activity.window.decorView
        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                setFullscreen(activity)
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun unregisterSystemUiVisibility() {
        val decorView = activity.window.decorView
        decorView.setOnSystemUiVisibilityChangeListener(null)
    }


    fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) {
            _handler.removeCallbacks(this)
            _handler.postDelayed(this, 300)
        } else {
            _handler.removeCallbacks(this)
        }
    }

    fun onKeyDown(keyCode: Int) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            _handler.removeCallbacks(this)
            _handler.postDelayed(this, 500)
        }
    }

    override fun onStop() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            playerA.setCardBackgroundColor(resources.getColor(default_color))
            playerB.setCardBackgroundColor(resources.getColor(default_color))
        }
        _handler.removeCallbacks(this)
        super.onStop()
    }

    override fun run() {
        setFullscreen(activity)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT > 10) {
            unregisterSystemUiVisibility()
        }
        exitFullscreen(activity)
    }


    fun exitFullscreen(activity: Activity) {
        if (Build.VERSION.SDK_INT > 10) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        } else {
            activity.window
                    .setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }
    }

    companion object {


        fun convertStringToLong(time: String): Long {

            val all = time.toCharArray()

            var number = (java.lang.Long.parseLong(Character.toString(all[0])) * 10 + java.lang.Long.parseLong(Character.toString(all[1]))) * 60
            number += java.lang.Long.parseLong(Character.toString(all[3])) * 10 + java.lang.Long.parseLong(Character.toString(all[4]))

            return number
        }

        val isImmersiveAvailable: Boolean
            get() = android.os.Build.VERSION.SDK_INT >= 19
    }
}