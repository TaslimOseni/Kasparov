package com.dabinu.apps.chesstimer


import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.dabinu.apps.chesstimer.fragments.MenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, MenuFragment())
        fragmentTransaction.commit()
    }


    override fun onBackPressed() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, MenuFragment())
        fragmentTransaction.commit()
    }
}