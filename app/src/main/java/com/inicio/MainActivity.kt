package com.inicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.inicio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btRegister.setOnClickListener { registrar() }
        binding.btLogin.setOnClickListener { login() }
    }
    private fun registrar() {
        val email = binding.etCorreo.text.toString()
        val clave = binding.etContraseA.text.toString()

        //Se usa la función para crear un usuario por medio de correo y contraseña
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    Toast.makeText(baseContext,
                        getString(R.string.msg_fallo_registro),
                        Toast.LENGTH_SHORT).show()
                    actualiza(null)
                }
            }
    }

    private fun login() {
        val email = binding.etCorreo.text.toString()
        val clave = binding.etContraseA.text.toString()

        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    Toast.makeText(baseContext,
                        getString(R.string.msg_fallo_login),
                        Toast.LENGTH_SHORT).show()
                    actualiza(null)
                }
            }
    }

    private fun actualiza(user: FirebaseUser?) {
        if (user!=null) {
            val intent = Intent(this,Central::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        actualiza(user)
    }
}