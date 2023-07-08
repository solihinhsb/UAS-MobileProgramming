package com.kelompok3.absensi.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kelompok3.absensi.R
import com.kelompok3.absensi.utils.SessionLogin
import com.kelompok3.absensi.view.absen.AbsenActivity
import com.kelompok3.absensi.view.history.HistoryActivity
import com.kelompok3.absensi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var strTitle: String
    private lateinit var session: SessionLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitLayout()
    }

    private fun setInitLayout() {
        session = SessionLogin(this)
        session.checkLogin()

        binding.cvAbsenMasuk.setOnClickListener {
            strTitle = "Absen Masuk"
            val intent = Intent(this@MainActivity, AbsenActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        binding.cvAbsenKeluar.setOnClickListener {
            strTitle = "Absen Keluar"
            val intent = Intent(this@MainActivity, AbsenActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        binding.cvPerizinan.setOnClickListener {
            strTitle = "Izin"
            val intent = Intent(this@MainActivity, AbsenActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        binding.cvHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.imageLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Yakin Anda ingin Logout?")
            builder.setCancelable(true)
            builder.setNegativeButton("Batal") { dialog, which -> dialog.cancel() }
            builder.setPositiveButton("Ya") { dialog, which ->
                session.logoutUser()
                finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}
