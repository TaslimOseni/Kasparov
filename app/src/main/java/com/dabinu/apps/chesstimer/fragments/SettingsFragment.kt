package com.dabinu.apps.chesstimer.fragments


import android.app.FragmentTransaction
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.dabinu.apps.chesstimer.R
import kotlinx.android.synthetic.main.fragment_settings.*

import java.util.Arrays


class SettingsFragment : android.app.Fragment(), View.OnClickListener {


    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var fragmentTransaction: FragmentTransaction


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        return view

    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        setSavedArrangement()

        save_text.setOnClickListener(this)

    }

    fun init() {

        sharedPreferences = activity.getSharedPreferences("DETAILS", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()


        val active_color_adapter = ArrayAdapter.createFromResource(activity.applicationContext, R.array.colors, R.layout.spinnerheader)
        active_color_adapter.setDropDownViewResource(R.layout.drop_down)
        spinner_active_color.adapter = active_color_adapter


        val inactive_color_adapter = ArrayAdapter.createFromResource(activity.applicationContext, R.array.colors, R.layout.spinnerheader)
        inactive_color_adapter.setDropDownViewResource(R.layout.drop_down)
        spinner_inactive_color.adapter = inactive_color_adapter


        val def_color_adapter = ArrayAdapter.createFromResource(activity.applicationContext, R.array.colors, R.layout.spinnerheader)
        def_color_adapter.setDropDownViewResource(R.layout.drop_down)
        spinner_default_color.adapter = def_color_adapter


        val bkd_color_adapter = ArrayAdapter.createFromResource(activity.applicationContext, R.array.bkd_colors, R.layout.spinnerheader)
        bkd_color_adapter.setDropDownViewResource(R.layout.drop_down)
        spinner_background_color.adapter = bkd_color_adapter

    }


    fun setSavedArrangement() {
        should_vibrate.isChecked = sharedPreferences.getString("vibrate", "true") == "true"
        should_ring.isChecked = sharedPreferences.getString("ring", "true") == "true"
        spinner_active_color.setSelection(Arrays.asList(*resources.getStringArray(R.array.colors)).indexOf(sharedPreferences.getString("active_color", "Green")))
        spinner_inactive_color.setSelection(Arrays.asList(*resources.getStringArray(R.array.colors)).indexOf(sharedPreferences.getString("inactive_color", "Red")))
        spinner_default_color.setSelection(Arrays.asList(*resources.getStringArray(R.array.colors)).indexOf(sharedPreferences.getString("default_color", "Gray")))
        spinner_background_color.setSelection(Arrays.asList(*resources.getStringArray(R.array.bkd_colors)).indexOf(sharedPreferences.getString("background_color", "Deep-Gray")))
    }


    override fun onClick(view: View) {

        if (spinner_active_color.selectedItem as String == spinner_inactive_color.selectedItem as String) {
            Toast.makeText(activity.applicationContext, "Active and inactive color must be different", Toast.LENGTH_SHORT).show()
        } else if (spinner_active_color.selectedItem as String == spinner_default_color.selectedItem as String) {
            Toast.makeText(activity.applicationContext, "Active and default color must be different", Toast.LENGTH_SHORT).show()
        } else if (spinner_active_color.selectedItem as String == spinner_background_color.selectedItem as String) {
            Toast.makeText(activity.applicationContext, "Active and background color must be different", Toast.LENGTH_SHORT).show()
        } else if (spinner_inactive_color.selectedItem as String == spinner_default_color.selectedItem as String) {
            Toast.makeText(activity.applicationContext, "Inactive and default color must be different", Toast.LENGTH_SHORT).show()
        } else if (spinner_inactive_color.selectedItem as String == spinner_background_color.selectedItem as String) {
            Toast.makeText(activity.applicationContext, "Inactive and background color must be different", Toast.LENGTH_SHORT).show()
        } else if (spinner_default_color.selectedItem as String == spinner_background_color.selectedItem as String) {
            Toast.makeText(activity.applicationContext, "Default and background color must be different", Toast.LENGTH_SHORT).show()
        } else {
            editor.putString("vibrate", if (should_vibrate.isChecked) "true" else "false")
            editor.putString("ring", if (should_ring.isChecked) "true" else "false")
            editor.putString("active_color", spinner_active_color.selectedItem as String)
            editor.putString("inactive_color", spinner_inactive_color.selectedItem as String)
            editor.putString("default_color", spinner_default_color.selectedItem as String)
            editor.putString("background_color", spinner_background_color.selectedItem as String)
            editor.apply()

            Toast.makeText(activity.applicationContext, "Saved", Toast.LENGTH_SHORT).show()

            goToHomeFragment()
        }
    }


    fun goToHomeFragment() {
        fragmentTransaction = activity.fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, MenuFragment())
        fragmentTransaction.commit()
    }


}