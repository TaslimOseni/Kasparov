package com.dabinu.apps.chesstimer.fragments


import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

import com.dabinu.apps.chesstimer.models.GameMode
import com.dabinu.apps.chesstimer.R
import kotlinx.android.synthetic.main.fragment_menu.*

import java.io.File
import java.io.ObjectInputStream
import java.io.Serializable
import java.util.ArrayList


class MenuFragment : android.app.Fragment(), Serializable{



    lateinit var allModes: ArrayList<GameMode>
    lateinit var allModeNames: ArrayList<String>
    lateinit var allTimeInMinutes: ArrayList<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllModes()
        getAllTimeInMinutes()
        init()

    }


    fun init() {

        start_game.setOnClickListener{
            var sharedPreferences: SharedPreferences = activity.getSharedPreferences("GAME", Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor = sharedPreferences.edit()

            if (mode_spinner.selectedItem == "Normal") {
                editor.putString("duration", (time_spinner.selectedItem as String).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
                editor.putString("delay", "0")
                editor.apply()
            } else {
                editor.putString("duration", allModes[allModeNames.indexOf(mode_spinner.selectedItem as String)].duration)
                editor.putString("delay", allModes[allModeNames.indexOf(mode_spinner.selectedItem as String)].delay)
                editor.apply()
            }
            goToFragement(TimerFragment())
        }

        settings.setOnClickListener{goToFragement(SettingsFragment())}
        about.setOnClickListener{goToFragement(AboutFragment())}



        val time_adapter = ArrayAdapter(activity.applicationContext, R.layout.spinnerheader, allTimeInMinutes)
        time_adapter.setDropDownViewResource(R.layout.drop_down)
        time_spinner.adapter = time_adapter
        time_spinner.setSelection(4)


        val mode_adapter = ArrayAdapter(activity.applicationContext, R.layout.spinnerheader, allModeNames)
        mode_adapter.setDropDownViewResource(R.layout.drop_down)
        mode_spinner.adapter = mode_adapter
        mode_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if (i == 0) {
                    setTimerAdapter(true)
                } else if (i == allModes.size - 1) {
                    goToFragement(NewGameModeFragment())
                } else {
                    setTimerAdapter(false)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }

    }


    fun getAllModes() {
        allModes = ArrayList()
        allModeNames = ArrayList()

        allModes.add(GameMode("Normal", "0", "0"))
        allModeNames.add("Normal")


        val files = activity.filesDir.listFiles()

        for (i in files) {
            try {
                val gameMode = ObjectInputStream(activity.openFileInput(i.name)).readObject() as GameMode
                allModes.add(gameMode)
                allModeNames.add(String.format("%s %s|%s", gameMode.name, gameMode.duration, gameMode.delay))
            } catch (e: Exception) {

            }

        }


        allModes.add(GameMode("Fischer Blitz", "0", "5"))
        allModes.add(GameMode("Delay Bullet", "2", "1"))
        allModes.add(GameMode("Fischer", "5", "5"))
        allModes.add(GameMode("Add new", "", ""))

        allModeNames.add("Fischer Blitz 5|0")
        allModeNames.add("Delay Bullet 1|2")
        allModeNames.add("Fischer 5|5")
        allModeNames.add("ADD NEW MODE")

    }


    fun getAllTimeInMinutes() {
        allTimeInMinutes = ArrayList()

        for (i in 1..60) {
            allTimeInMinutes.add(String.format("%d min", i))
        }
    }


    fun goToFragement(fragment: Fragment) {
        var fragmentTransaction: FragmentTransaction = activity.fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }



    fun setTimerAdapter(isZero: Boolean) {
        if (isZero) {
            val time_adapter = ArrayAdapter(activity.applicationContext, R.layout.spinnerheader, allTimeInMinutes)
            time_adapter.setDropDownViewResource(R.layout.drop_down)
            time_spinner.adapter = time_adapter
            time_spinner.setSelection(4)
        } else {
            val time_adapter = ArrayAdapter.createFromResource(activity.applicationContext, R.array.no_time, R.layout.spinnerheader)
            time_adapter.setDropDownViewResource(R.layout.drop_down)
            time_spinner.adapter = time_adapter
        }
    }

}