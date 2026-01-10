package app.kawamura.kako.orijinaru.data.db

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.kawamura.kako.orijinaru.R
import app.kawamura.kako.orijinaru.databinding.ActivityMistakesResultBinding
import app.kawamura.kako.orijinaru.ui.subject.SelectSubjectActivity

class MistakesResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMistakesResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMistakesResultBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val quizCount = intent.getIntExtra("QuizCount",0)
        val correctCount = intent.getIntExtra("CorrectCount",0)

        binding.quizCountText.text = quizCount.toString()
        binding.correctCountText.text = correctCount.toString()

        if (correctCount == quizCount) {
            binding.resultImage.setImageResource(R.drawable.gold)
        } else if (correctCount / quizCount > 0.5) {
            binding.resultImage.setImageResource(R.drawable.silver)
        } else {
            binding.resultImage.setImageResource(R.drawable.copper)
        }

        //back_buttonがタップされたときに
        binding.backButton.setOnClickListener {
            //画面移動の準備をする
            val intent : Intent = Intent(this, SelectSubjectActivity::class.java)
            startActivity(intent)
        }
    }
}