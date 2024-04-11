package dev.cagrisayir.guessthenumbergame

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.cagrisayir.guessthenumbergame.databinding.ActivityPredictBinding
import kotlin.random.Random
import kotlin.random.nextInt

class PredictActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPredictBinding
    private var health = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val randomNumber = Random.nextInt(0, 101)
        Log.e("Random Number", "$randomNumber")

        binding.guessButton.setOnClickListener {
            val userNumber = binding.editTextNumber.text.toString().toInt()
            if (userNumber == randomNumber) {
                val intent = Intent(this@PredictActivity, ResultActivity::class.java)
                intent.putExtra("result", "win")
                startActivity(intent)
            } else if (userNumber > randomNumber) {
                health--
                if (health == 0) {
                    intent = Intent(this@PredictActivity, ResultActivity::class.java)
                    intent.putExtra("result", "lost")
                    startActivity(intent)
                }
                binding.artirAzaltText.setText("Please enter a lower number")
                binding.artirAzaltText.visibility = View.VISIBLE
            } else {
                health--
                if (health == 0) {
                    intent = Intent(this@PredictActivity, ResultActivity::class.java)
                    intent.putExtra("result", "lost")
                    startActivity(intent)
                }
                binding.artirAzaltText.setText("Please enter a higher number")
                binding.artirAzaltText.visibility = View.VISIBLE
            }
            binding.numberOfTryText.setText("Health: $health")
        }

    }
}