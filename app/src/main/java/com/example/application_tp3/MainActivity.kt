package com.example.application_tp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var buttonOK: Button
    private lateinit var buttonCancel: Button
    private lateinit var filename: String
    private lateinit var file: File
    private lateinit var fileContents: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nomB_1 = "Tape-Wali"
        val nomB_2 = "Traore-Zie"
        val Contenu = "Bonjour Feuillatre Helene"

        val file = File(
            this.getExternalFilesDir(null),
            nomB_1.plus("__").plus(nomB_2).plus(".txt"))
        file.createNewFile()

        // Ecriture dans le fichier
        FileOutputStream(file).use {
            it.write(Contenu.toByteArray())
        }
        // La lecture du contenu du fichier
        val fileContents = FileInputStream(file).bufferedReader().use {
            it.readText()
        }
        /*
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = fileContents
        */
        val editText = findViewById<EditText>(R.id.editText)
        val buttonOK = findViewById<Button>(R.id.buttonOK)
        val junior = "Junior Aristide"
        Log.d("valeur", editText.text.toString())

    }
}