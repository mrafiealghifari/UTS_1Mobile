package com.example.uts_1mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnRegister) // Menggunakan ID yang ada di layout Anda

        btnLogin.setOnClickListener {
            val name = etName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Nama tidak boleh kosong"
                return@setOnClickListener
            }

            if (password.length < 6) {
                tilPassword.error = "Password minimal 6 karakter"
                return@setOnClickListener
            }

            // Sukses Login: Navigasi ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
            finish() // Agar tidak bisa balik ke login dengan tombol back
        }
    }
}