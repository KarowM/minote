package com.minote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val notesList : ArrayList<Note>, private val listener: (Note) -> Unit?) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteView = LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        return ViewHolder(noteView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.itemView.setOnClickListener { listener(currentNote) }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class ViewHolder(noteView: View) : RecyclerView.ViewHolder(noteView) {

        val title : TextView = noteView.findViewById(R.id.noteTitle)
    }
}