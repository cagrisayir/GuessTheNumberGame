package dev.cagrisayir.guessthenumbergame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.cagrisayir.guessthenumbergame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getStringExtra("result")
        if (result.equals("win")) {
            binding.resultText.text = "YOU WON THE GAME!"
            binding.resultText.setTextColor(Color.GREEN)
            binding.resultImage.setImageResource(R.drawable.success)
        }

        binding.restartButton.setOnClickListener {
            val intent = Intent(this, PredictActivity::class.java)
            startActivity(intent)
        }
    }
}