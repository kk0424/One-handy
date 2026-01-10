package app.kawamura.kako.orijinaru.ui.subject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.kawamura.kako.orijinaru.R
import app.kawamura.kako.orijinaru.data.db.AppDatabase
import app.kawamura.kako.orijinaru.data.db.MistakesActivity
import app.kawamura.kako.orijinaru.data.db.QuestionDao
import app.kawamura.kako.orijinaru.data.db.mathQuestionList
import app.kawamura.kako.orijinaru.databinding.ActivitySelectMathBinding
import app.kawamura.kako.orijinaru.ui.QuestionActivity

class SelectMathActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivitySelectMathBinding
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
        binding = ActivitySelectMathBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        dao = AppDatabase.getInstance(this).questionDao()
        val mistakes = dao.takeMistakes("Math")
        if (mistakes.isEmpty()){
            binding.mistakesButton.setBackgroundColor(Color.rgb(255,144,84))
        } else {
            binding.mistakesButton.setBackgroundColor(Color.rgb(157,166,253))
        }

        //val pref4: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        //val isAllCorrectMath = getArray("StringItem6")!!
        val CorrectTriangle1Quiz = dao.takeCorrectQuiz("Triangle1Quiz")
        val CorrectTriangle2Quiz = dao.takeCorrectQuiz("Triangle2Quiz")
        val CorrectTriangle3Quiz = dao.takeCorrectQuiz("Triangle3Quiz")
        if (CorrectTriangle1Quiz.size == 10){
            binding.questionTriangle1Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionTriangle1Button.setBackgroundColor(Color.rgb(157,166,253))
        }
        if (CorrectTriangle2Quiz.size == 10){
            binding.questionTriangle2Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionTriangle2Button.setBackgroundColor(Color.rgb(157,166,253))
        }
        if (CorrectTriangle3Quiz.size == 10){
            binding.questionTriangle3Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionTriangle3Button.setBackgroundColor(Color.rgb(157,166,253))
        }
        //saveArray(isAllCorrectMath,"StringItem3")

        //三角比①がタップされたとき
        binding.questionTriangle1Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Triangle1Quiz")
            questionIntent.putExtra("buttonName","questionTriangle1Button")
            questionIntent.putExtra("Subject3", "Math")
            startActivity(questionIntent)
        }

        //三角比②がタップされたとき
        binding.questionTriangle2Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Triangle2Quiz")
            questionIntent.putExtra("buttonName","questionTriangle2Button")
            questionIntent.putExtra("Subject3", "Math")
            startActivity(questionIntent)
        }

        //三角比③がタップされたとき
        binding.questionTriangle3Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Triangle3Quiz")
            questionIntent.putExtra("buttonName","questionTriangle3Button")
            questionIntent.putExtra("Subject3", "Math")
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
                mistakesIntent.putExtra("subject","Math")
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