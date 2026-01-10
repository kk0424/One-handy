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
import app.kawamura.kako.orijinaru.databinding.ActivitySelectScienceBinding
import app.kawamura.kako.orijinaru.ui.QuestionActivity

class SelectScienceActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivitySelectScienceBinding
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
//        val savedString = prefs.getString(prefKey, "×,×,×")
//
//        return if (!savedString.isNullOrEmpty()) {
//            savedString.split(",").toTypedArray()
//        } else {
//            null
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectScienceBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //val pref5: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        dao = AppDatabase.getInstance(this).questionDao()
        val mistakes = dao.takeMistakes("Science")
        if (mistakes.isEmpty()) {
            binding.mistakesButton.setBackgroundColor(Color.rgb(255,144,84))
        } else {
            binding.mistakesButton.setBackgroundColor(Color.rgb(136,234,157))
        }
        //val isAllCorrectScience = getArray("StringItem8")!!
        val CorrectSolution1Quiz = dao.takeCorrectQuiz("Solution1Quiz")
        val CorrectSolution2Quiz = dao.takeCorrectQuiz("Solution2Quiz")
        if (CorrectSolution1Quiz.size == 10){
            binding.questionIon1Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionIon1Button.setBackgroundColor(Color.rgb(136,234,157))
        }
        if (CorrectSolution2Quiz.size == 10){
            binding.questionIon2Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionIon2Button.setBackgroundColor(Color.rgb(136,234,157))
        }
        //saveArray(isAllCorrectScience,"StringItem7")

        //イオン①がタップされたとき
        binding.questionIon1Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Solution1Quiz")
            questionIntent.putExtra("buttonName","questionIon1Button")
            questionIntent.putExtra("Subject3", "Science")
            startActivity(questionIntent)
        }

        //イオン②がタップされたとき
        binding.questionIon2Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "Solution2Quiz")
            questionIntent.putExtra("buttonName","questionIon2Button")
            questionIntent.putExtra("Subject3", "Science")
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
                mistakesIntent.putExtra("subject","Science")
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