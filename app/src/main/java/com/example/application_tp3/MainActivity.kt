package com.example.application_tp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOK = findViewById<Button>(R.id.buttonOK)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)
        val editText = findViewById<EditText>(R.id.editText)
        lateinit var fileContents: String

        val nomB_1 = "Tape-Wali"
        val nomB_2 = "Traore-Zie"
        val Contenu = "Bonjour Feuillatre Helene"

        val file = File(
            this.getExternalFilesDir(null),
            nomB_1.plus("__").plus(nomB_2).plus(".txt"))
        // Création du fichier
        file.createNewFile()

        if (!file.exists()) {
            // Le fichier n'existe pas alors

            // Ecriture dans le fichier
            FileOutputStream(file).use {
                it.write(Contenu.toByteArray())
            }
        }

        else {

            /*
            // La lecture du contenu du fichier
            val fileContents = FileInputStream(file).bufferedReader().use {
                it.readText()
            }
            */


            // Fonction du bouton OK
            buttonOK.setOnClickListener {
                FileOutputStream(file).use { stream ->
                    // On écrase le contenu le contenu du fichier par le contenu de la zone de saisie
                    stream.write(editText.text.toString().toByteArray())
                }
                Toast.makeText(this, "Contenu du fichier mis à jour", Toast.LENGTH_SHORT).show()
                Log.d("Tache","Bouton Ok pressed")
            }

            // Fonction du bouton Cancel
            buttonCancel.setOnClickListener {
                // On écrase le contenu de la zone de saisie par le contenu du fichier
                fileContents = FileInputStream(file).bufferedReader().use { it.readText() }
                editText.setText(fileContents)
                Toast.makeText(this, "Contenu du champ texte restauré", Toast.LENGTH_SHORT).show()
                Log.d("Tache","Bouton Cancel pressed")
            }

        }

    }
}