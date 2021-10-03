package com.minote

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class NotesActivity : AppCompatActivity() {

    private lateinit var noteRecyclerView: RecyclerView
    private lateinit var notesList: ArrayList<Note>
    private lateinit var dialog: Dialog;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        dialog = Dialog(this)
        notesList = ArrayList()

        noteRecyclerView = findViewById(R.id.recyclerView)
        noteRecyclerView.layoutManager = LinearLayoutManager(this)
        noteRecyclerView.adapter = NotesAdapter(notesList, openNote())

        val queue = Volley.newRequestQueue(this)
        val url = "URL"

        val notesRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                run {
                    if (response.length() > 0) {
                        val s: TextView = findViewById(R.id.notesEmpty)
                        s.visibility = View.GONE
                    }
                    for (i in 0 until response.length()) {
                        notesList.add(Note(response.getJSONObject(i).get("title").toString(), response.getJSONObject(i).get("body").toString()))
                    }
                    noteRecyclerView.adapter = NotesAdapter(notesList, openNote())
                }
            },
            { println("Failed to display notes data from API") }
        )

        queue.add(notesRequest)
    }

    private fun openNote() = { item: Note ->
        dialog.setContentView(R.layout.note_popup)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        dialog.findViewById<TextView?>(R.id.popupNoteTitle).text = item.title
        dialog.findViewById<TextView?>(R.id.popupNoteBody).text = item.body
    }
}