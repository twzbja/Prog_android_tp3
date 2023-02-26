package com.example.tp3_q5_a_q7

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var buttonOK: Button
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        buttonOK = findViewById(R.id.buttonOK)
        listView = findViewById(R.id.listView)

        buttonOK.setOnClickListener {
            val filename = editText.text.toString()
            if (filename.isNotEmpty()) {
                val file = File(getExternalFilesDir(null), filename.plus(".txt"))
                file.createNewFile()
                Toast.makeText(this, "Fichier créé : $filename", Toast.LENGTH_SHORT).show()
                updateListView()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Le champ texte est vide.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
        updateListView()
    }

    private fun updateListView() {
        val fileNames = getExternalFilesDir(null)?.list() ?: emptyArray()

        listView.adapter = object : ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, fileNames) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)

                val buttonDelete = view.findViewById<Button>(R.id.buttonDelete)
                buttonDelete.setOnClickListener {
                    val filename = fileNames[position]
                    val file = File(getExternalFilesDir(null), filename)
                    file.delete()
                    Toast.makeText(this@MainActivity, "Fichier supprimé : $filename", Toast.LENGTH_SHORT).show()
                    updateListView()
                }

                return view
            }
        }

        listView.visibility = if (fileNames.isEmpty()) View.GONE else View.VISIBLE

    }

}
