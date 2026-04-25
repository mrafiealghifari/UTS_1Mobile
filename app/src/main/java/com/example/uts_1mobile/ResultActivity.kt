package com.example.uts_1mobile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // 1. Tangkap Data dari Intent
        val name = intent.getStringExtra("NAME")
        val email = intent.getStringExtra("EMAIL")
        val phone = intent.getStringExtra("PHONE")
        val gender = intent.getStringExtra("GENDER")
        val seminar = intent.getStringExtra("SEMINAR")

        // 2. Inisialisasi View (Sesuaikan ID dengan XML kamu)
        val tvName = findViewById<TextView>(R.id.tvResName)
        val tvEmail = findViewById<TextView>(R.id.tvResEmail)
        val tvPhone = findViewById<TextView>(R.id.tvResPhone)
        val tvGender = findViewById<TextView>(R.id.tvResGender)
        val tvSeminar = findViewById<TextView>(R.id.tvResSeminar)
        val btnBack = findViewById<MaterialButton>(R.id.btnBackHome)

        // 3. Tampilkan Data
        tvName.text = name
        tvEmail.text = email
        tvPhone.text = phone
        tvGender.text = gender
        tvSeminar.text = seminar

        // 4. Tombol Kembali ke Halaman Utama
        btnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Menghapus tumpukan activity sebelumnya
            startActivity(intent)
            finish()
        }
    }
}