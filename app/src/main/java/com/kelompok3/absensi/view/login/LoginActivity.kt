package com.kelompok3.absensi.view.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kelompok3.absensi.R
import com.kelompok3.absensi.databinding.ActivityLoginBinding
import com.kelompok3.absensi.utils.SessionLogin
import com.kelompok3.absensi.view.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var session: SessionLogin
    private lateinit var strNama: String
    private lateinit var strPassword: String
    private val REQ_PERMISSION = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission()
        setInitLayout()
    }

    private fun setPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQ_PERMISSION
            )
        }
    }

    private fun setInitLayout() {
        session = SessionLogin(applicationContext)

        if (session.isLoggedIn()) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            strNama = binding.inputNama.text.toString()
            strPassword = binding.inputPassword.text.toString()

            if (strNama.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Form tidak boleh kosong!",
                    Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                session.createLoginSession(strNama)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
