package com.minote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val noteList: ListView = findViewById(R.id.notes)

        val listItems = ArrayList<String>()
        listItems.add("hello")
        listItems.add("world")
        noteList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
    }
}