package com.example.login1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class registrar : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registrarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)


        mAuth = FirebaseAuth.getInstance()
// Después de inicializar registrarButton
        val salirButton: Button = findViewById(R.id.btnSalir)
        emailEditText = findViewById(R.id.emailRegistro)
        passwordEditText = findViewById(R.id.passwordRegistro)
        registrarButton = findViewById(R.id.btnRegistrar)

        registrarButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registro exitoso, actualiza la interfaz de usuario con la información del usuario registrado
                        Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()
                        // Aquí puedes redirigir a otra actividad si el registro es exitoso
                    } else {
                        // Si el registro falla, muestra un mensaje al usuario.
                        Toast.makeText(this, "Error al registrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        salirButton.setOnClickListener {
            // Aquí colocamos el código para iniciar MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Esto terminará la actividad actual para que no pueda volver atrás con el botón de retroceso
        }
    }
}
