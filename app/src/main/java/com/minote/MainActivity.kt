package com.minote

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getNotes(view: View) {
        println("button clicked!")

        val queue = Volley.newRequestQueue(this)
        val url = "URL"

        val notesRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response -> println(response.get(0)) },
            { println("That didn't work!") }
        )

        queue.add(notesRequest)

        val intent = Intent(applicationContext, NotesActivity::class.java)
        startActivity(intent)
    }
}