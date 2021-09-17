package com.minote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class NotesActivity : AppCompatActivity() {

    private lateinit var noteRecyclerView: RecyclerView
    private lateinit var notesList: ArrayList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notesList = ArrayList()

        noteRecyclerView = findViewById(R.id.recyclerView)
        noteRecyclerView.layoutManager = LinearLayoutManager(this)
        noteRecyclerView.adapter = NotesAdapter(notesList) { item -> println("Clicked note: $item") }
        notesList.add(Note("The first note!", "First note body"))
        notesList.add(Note("The second note!", "Second note Body"))

        val queue = Volley.newRequestQueue(this)
        val url = "URL"

        val notesRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                run {
                    for (i in 0 until response.length()) {
                        notesList.add(Note(response.getJSONObject(i).get("title").toString(), response.getJSONObject(i).get("body").toString()))
                    }
                    noteRecyclerView.adapter = NotesAdapter(notesList) { item -> println("Clicked note: $item") }
                }
            },
            { println("Failed to display notes data from API") }
        )

        queue.add(notesRequest)
    }
}