package app.kawamura.kako.orijinaru.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.kawamura.kako.orijinaru.R
import app.kawamura.kako.orijinaru.databinding.ActivityResultBinding
import app.kawamura.kako.orijinaru.ui.subject.SelectSubjectActivity

class ResultActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivityResultBinding

//    private fun saveArray(array: Array<String>, prefKey: String) {
//        if (array.isEmpty()) return
//
//        val joinedString = array.joinToString(",") // "aaa,bbb,ccc,..."
//
//        val prefs = getSharedPreferences("Array", Context.MODE_PRIVATE)
//
//        prefs.edit().putString(prefKey, joinedString).apply()
//
//    }
//
//    private fun getArray(prefKey: String): Array<String>? {
//        val prefs = getSharedPreferences("Array", Context.MODE_PRIVATE)
//
//        val savedString = prefs.getString(prefKey, null)
//
//        return if (!savedString.isNullOrEmpty()) {
//            savedString.split(",").toTypedArray()
//        } else {
//            null
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        //インテントからクイズ数と正解数を受け取る
        val quizCount = intent.getIntExtra("QuizCount",0)
        val correctCount = intent.getIntExtra("CorrectCount",0)
        val timeCount = intent.getDoubleExtra("TimeCount",0.0)
        val buttonName = intent.getStringExtra("buttonName")
        val subject6 = intent.getStringExtra("Subject5")

        //val isAllCorrectSubject = getArray("StringItem11")!!

        //クイズ数と正解数をテキストに反映させる
        binding.quizCountText.text = quizCount.toString()
        binding.correctCountText.text = correctCount.toString()
        binding.timecountText.text = ("%.1f".format(timeCount))

        //正解数によってカップの色を変える
        if (correctCount == 10) {
            binding.resultImage.setImageResource(R.drawable.gold)
//            val editor = pref.edit()
//            editor.putString("DateKey",buttonName.toString())
//            editor.apply()
        } else if (5<correctCount) {
            binding.resultImage.setImageResource(R.drawable.silver)
        } else {
            binding.resultImage.setImageResource(R.drawable.copper)
        }

        //back_buttonがタップされたときに
        binding.backButton.setOnClickListener {
            //画面移動の準備をする
            val intent :Intent = Intent(this, SelectSubjectActivity::class.java)
            startActivity(intent)
        }
    }
}