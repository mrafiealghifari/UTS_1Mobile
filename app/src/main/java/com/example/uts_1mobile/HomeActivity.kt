package com.example.uts_1mobile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        val btnDaftarSeminar = findViewById<MaterialButton>(R.id.btnDaftarSeminar)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Ambil data username dari intent
        val userName = intent.getStringExtra("USER_NAME")
        tvUserName.text = userName ?: "Rafie"

        // Tombol Kembali ke Login
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Navigasi ke Halaman Registrasi
        btnDaftarSeminar.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}