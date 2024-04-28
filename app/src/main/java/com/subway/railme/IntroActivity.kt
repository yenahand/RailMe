package com.subway.railme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.subway.railme.databinding.ActivityIntroBinding
import com.subway.railme.login.LoginActivity

class IntroActivity : AppCompatActivity() {
    private var _binding:ActivityIntroBinding?=null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Actionbar 제거
        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            Intent(this, LoginActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 1800)
    }

}