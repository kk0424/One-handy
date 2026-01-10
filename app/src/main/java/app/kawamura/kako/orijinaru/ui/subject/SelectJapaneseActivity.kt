package app.kawamura.kako.orijinaru.ui.subject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.kawamura.kako.orijinaru.R
import app.kawamura.kako.orijinaru.data.db.AppDatabase
import app.kawamura.kako.orijinaru.data.db.MistakesActivity
import app.kawamura.kako.orijinaru.data.db.Question
import app.kawamura.kako.orijinaru.data.db.QuestionDao
import app.kawamura.kako.orijinaru.databinding.ActivitySelectJapaneseBinding
import app.kawamura.kako.orijinaru.ui.QuestionActivity

class SelectJapaneseActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivitySelectJapaneseBinding
    private lateinit var dao: QuestionDao

//    private fun saveArray(array: Array<String>, prefKey: String) {
//        if (array.isEmpty()) return
//
//        val joinedString = array.joinToString(",") // "aaa,bbb,ccc,..."
//
//        val prefs = getSharedPreferences("Array", Context.MODE_PRIVATE)
//        prefs.edit().putString(prefKey, joinedString).apply()
//    }
//
//    private fun getArray(prefKey: String): Array<String>? {
//        val prefs = getSharedPreferences("Array", Context.MODE_PRIVATE)
//        val savedString = prefs.getString(prefKey, "×,×,×,×")
//
//        return if (!savedString.isNullOrEmpty()) {
//            savedString.split(",").toTypedArray()
//        } else {
//            null
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectJapaneseBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        //val pref3: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        dao = AppDatabase.getInstance(this).questionDao()
        val mistakes = dao.takeMistakes("Japanese")
        if (mistakes.isEmpty()) {
            binding.mistakesButton.setBackgroundColor(Color.rgb(255, 144, 84))
        } else {
            binding.mistakesButton.setBackgroundColor(Color.rgb(248,151,151))
        }
        //val isAllCorrectJapanese = getArray("StringItem4")!!
        val CorrectWord1Quiz = dao.takeCorrectQuiz("Word1Quiz")!!
        val CorrectWord2Quiz = dao.takeCorrectQuiz("Word2Quiz")!!
        val CorrectWord3Quiz = dao.takeCorrectQuiz("Word3Quiz")!!
        val CorrectWord4Quiz = dao.takeCorrectQuiz("Word4Quiz")!!
        if (CorrectWord1Quiz.size == 10) {
            binding.questionWord1Button.setBackgroundColor(Color.rgb(255, 144, 84))
        }else{
            binding.questionWord1Button.setBackgroundColor(Color.rgb(248,151,151))
        }
        if (CorrectWord2Quiz.size == 10) {
            binding.questionWord2Button.setBackgroundColor(Color.rgb(255, 144, 84))
        }else{
            binding.questionWord2Button.setBackgroundColor(Color.rgb(248,151,151))
        }
        if (CorrectWord3Quiz.size == 10) {
            binding.questionWord3Button.setBackgroundColor(Color.rgb(255, 144, 84))
        }else{
            binding.questionWord3Button.setBackgroundColor(Color.rgb(248,151,151))
        }
        if (CorrectWord4Quiz.size == 10) {
            binding.questionWord4Button.setBackgroundColor(Color.rgb(255, 144, 84))
        }else{
            binding.questionWord4Button.setBackgroundColor(Color.rgb(248,151,151))
        }

        //saveArray(isAllCorrectJapanese, "StringItem3")

        //古文単語①がタップされたとき
        binding.questionWord1Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Word1Quiz")
            questionIntent.putExtra("buttonName", "questionWord1Button")
            questionIntent.putExtra("Subject3", "Japanese")
            startActivity(questionIntent)
        }

        //古文単語②がタップされたとき
        binding.questionWord2Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Word2Quiz")
            questionIntent.putExtra("buttonName", "questionWord2Button")
            questionIntent.putExtra("Subject3", "Japanese")
            startActivity(questionIntent)
        }

        //古文単語③がタップされたとき
        binding.questionWord3Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Word3Quiz")
            questionIntent.putExtra("buttonName", "questionWord3Button")
            questionIntent.putExtra("Subject3", "Japanese")
            startActivity(questionIntent)
        }

        //古文単語④がタップされたとき
        binding.questionWord4Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Word4Quiz")
            questionIntent.putExtra("buttonName", "questionWord4Button")
            questionIntent.putExtra("Subject3", "Japanese")
            startActivity(questionIntent)
        }

        //間違えた問題のボタンがタップされたとき
        binding.mistakesButton.setOnClickListener {
            if (mistakes.isEmpty()) {
                val layout: View = layoutInflater.inflate(R.layout.toast_layout, findViewById(R.id.container))
                val text = layout.findViewById<TextView>(R.id.text)
                text.text = "間違えた問題はないよ！"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 100)
                toast.duration = Toast.LENGTH_LONG
                toast.view = layout
                toast.show()
            } else {
                val mistakesIntent: Intent = Intent(this, MistakesActivity::class.java)
                mistakesIntent.putExtra("subject","Japanese")
                startActivity(mistakesIntent)
            }

        }

            //backボタンがタップされたとき
            binding.backButton.setOnClickListener {
                val backIntent: Intent = Intent(this, SelectSubjectActivity::class.java)
                startActivity(backIntent)
            }
        }
}