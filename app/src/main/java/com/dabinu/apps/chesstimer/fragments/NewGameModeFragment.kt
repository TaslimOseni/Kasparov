package com.dabinu.apps.chesstimer.fragments


import android.app.FragmentTransaction
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.dabinu.apps.chesstimer.models.GameMode
import com.dabinu.apps.chesstimer.R
import kotlinx.android.synthetic.main.fragment_new_game_mode.*

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.util.ArrayList
import java.util.Random


class NewGameModeFragment : android.app.Fragment(), View.OnClickListener {



    lateinit var delay_units: ArrayList<String>
    lateinit var duration_units: ArrayList<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {

        val view = inflater.inflate(R.layout.fragment_new_game_mode, container, false)
        init(view)


        return view
    }


    fun init(view: View) {

        initDelayUnits()

        val delay_adapter = ArrayAdapter(activity.applicationContext, R.layout.spinnerheader, delay_units)
        delay_adapter.setDropDownViewResource(R.layout.drop_down)
        delay.adapter = delay_adapter


        val duration_adapter = ArrayAdapter(activity.applicationContext, R.layout.spinnerheader, duration_units)
        duration_adapter.setDropDownViewResource(R.layout.drop_down)
        duration.adapter = duration_adapter


        save.setOnClickListener(this)
    }


    fun initDelayUnits() {
        delay_units = ArrayList()
        duration_units = ArrayList()

        delay_units.add("No delay")
        delay_units.add("1 second")

        duration_units.add("1 minute")

        for (i in 2..60) {
            duration_units.add(String.format("%s minutes", Integer.toString(i)))
            if (i <= 15) {
                delay_units.add(String.format("%s seconds", Integer.toString(i)))
            }
        }
    }

    override fun onClick(view: View) {
        if (name.text.toString().trim { it <= ' ' } == "") {
            name.error = "Input a valid name.."
        } else {
            val gameMode = GameMode(name.text.toString().trim { it <= ' ' }, if (delay.selectedItem as String == "No delay") "0" else (delay.selectedItem as String).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0], (duration.selectedItem as String).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])

            val random = Random()
            val code = random.nextInt(9000)
            try {
                val fos = activity.openFileOutput(Integer.toString(code), Context.MODE_PRIVATE)
                val oos = ObjectOutputStream(fos)
                oos.writeObject(gameMode)
                oos.close()
                fos.close()
                Toast.makeText(activity.applicationContext, "Saved successfully", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(activity.applicationContext, "Failed, try again later", Toast.LENGTH_LONG).show()
            }

            val fragmentTransaction = activity.fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, MenuFragment())
            fragmentTransaction.commit()

        }
    }
}// Required empty public constructor