package com.example.login1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button // Agregamos el botón de registro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.contraseña)
        loginButton = findViewById(R.id.button)
        registerButton = findViewById(R.id.button2) // Inicializamos el botón de registro

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, home::class.java)
                        startActivity(intent)
                        finish() // Para evitar que el usuario vuelva a esta actividad presionando el botón Atrás
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Agregamos el listener para el botón de registro
        registerButton.setOnClickListener {
            val intent = Intent(this, registrar::class.java)
            startActivity(intent)
        }
    }
}
