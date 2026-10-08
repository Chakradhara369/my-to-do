package com.example.mytodo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
        }

        val input = EditText(this).apply {
            hint = "Enter a task..."
        }

        val addButton = Button(this).apply {
            text = "Add Task"
        }

        val listView = ListView(this)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tasks
        )
        listView.adapter = adapter

        addButton.setOnClickListener {
            val text = input.text.toString().trim()
            if (text.isNotEmpty()) {
                tasks.add(text)
                adapter.notifyDataSetChanged()
                input.text.clear()
            }
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }

        layout.addView(input)
        layout.addView(addButton)
        layout.addView(listView)

        setContentView(layout)
    }
}
