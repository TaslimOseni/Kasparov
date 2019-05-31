package com.dabinu.apps.chesstimer.fragments


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.dabinu.apps.chesstimer.R


/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : android.app.Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {

        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val github = view.findViewById<Button>(R.id.github_link)

        github.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TaslimOseni/kasparov"))) }

        github.setOnLongClickListener {
            copyToClipboard(activity.applicationContext, "https://github.com/TaslimOseni/kasparov")
            Toast.makeText(activity.applicationContext, "Copied", Toast.LENGTH_LONG).show()
            true
        }

        return view
    }


    private fun copyToClipboard(context: Context, text: String) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
            clipboard.text = text
        } else {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Copied Text", text)
            clipboard.primaryClip = clip
        }
    }

}// Required empty public constructor