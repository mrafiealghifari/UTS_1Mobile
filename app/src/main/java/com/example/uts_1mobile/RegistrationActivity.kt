package com.example.uts_1mobile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Inisialisasi Views
        val etName = findViewById<TextInputEditText>(R.id.etRegName)
        val etEmail = findViewById<TextInputEditText>(R.id.etRegEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etRegPhone)
        val tilName = findViewById<TextInputLayout>(R.id.tilRegName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilRegEmail)
        val tilPhone = findViewById<TextInputLayout>(R.id.tilRegPhone)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spSeminar = findViewById<Spinner>(R.id.spSeminar)
        val cbAgreement = findViewById<CheckBox>(R.id.cbAgreement)
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmitReg)

        // Setup Spinner
        val listSeminar = arrayOf(
            "Mastering Public Speaking",
            "Seni Persuasi & Negosiasi",
            "Teknik Storytelling",
            "Body Language Mastery",
            "Public Speaking for Leaders"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSeminar)
        spSeminar.adapter = adapter

        // --- VALIDASI REAL-TIME ---
        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) tilName.error = "Nama tidak boleh kosong" else tilName.error = null
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().trim()
                if (input.isEmpty()) tilEmail.error = "Email tidak boleh kosong"
                else if (!input.contains("@")) tilEmail.error = "Email tidak valid"
                else tilEmail.error = null
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().trim()
                when {
                    input.isEmpty() -> tilPhone.error = "Nomor HP tidak boleh kosong"
                    !input.startsWith("08") -> tilPhone.error = "Wajib diawali '08'"
                    input.length < 10 || input.length > 13 -> tilPhone.error = "Panjang 10-13 digit"
                    !input.all { it.isDigit() } -> tilPhone.error = "Hanya boleh angka"
                    else -> tilPhone.error = null
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // --- LOGIKA TOMBOL SUBMIT DENGAN DIALOG KONFIRMASI ---
        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val seminar = spSeminar.selectedItem?.toString() ?: ""

            // 1. Validasi Mandatory Fields
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || selectedGenderId == -1) {
                Toast.makeText(this, "Harap isi semua field pendaftaran!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Validasi Checkbox
            if (!cbAgreement.isChecked) {
                Toast.makeText(this, "Anda harus menyetujui pernyataan data benar!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Validasi Error Status
            if (tilName.error != null || tilEmail.error != null || tilPhone.error != null) {
                Toast.makeText(this, "Mohon perbaiki data yang salah!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = findViewById<RadioButton>(selectedGenderId).text.toString()

            // 4. Implementasi MaterialAlertDialogBuilder (Soal Poin 4)
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Pendaftaran")
                .setMessage("Apakah data yang Anda isi sudah benar?")
                .setCancelable(false)
                .setPositiveButton("Ya") { dialog, _ ->
                    // --- FUNGSI PINDAH HALAMAN DITEMPATKAN DI SINI ---
                    val intent = Intent(this, ResultActivity::class.java).apply {
                        putExtra("NAME", name)
                        putExtra("EMAIL", email)
                        putExtra("PHONE", phone)
                        putExtra("GENDER", gender)
                        putExtra("SEMINAR", seminar)
                    }
                    startActivity(intent)
                    dialog.dismiss()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    // Menutup dialog dan tetap di halaman form
                    dialog.dismiss()
                }
                .show()
        }
    }
}