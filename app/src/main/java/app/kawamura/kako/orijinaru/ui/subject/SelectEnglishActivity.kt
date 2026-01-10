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
import app.kawamura.kako.orijinaru.databinding.ActivitySelectEnglishBinding
import app.kawamura.kako.orijinaru.ui.QuestionActivity

class SelectEnglishActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivitySelectEnglishBinding
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
//        val savedString = prefs.getString(prefKey, "×,×,×,×,×,×,×")
//
//        return if (!savedString.isNullOrEmpty()) {
//            savedString.split(",").toTypedArray()
//        } else {
//            null
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectEnglishBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //val pref2: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        dao = AppDatabase.getInstance(this).questionDao()
        val mistakes = dao.takeMistakes("English")
        if (mistakes.isEmpty()) {
            binding.mistakesButton.setBackgroundColor(Color.rgb(255,144,84))
        } else {
            binding.mistakesButton.setBackgroundColor(Color.rgb(220,155,248))
        }

        //val isAllCorrectEnglish = getArray("StringItem2")!!
//        Log.d("array",arrayItem1.contentToString())
        val CorrectEnglishWord1Quiz = dao.takeCorrectQuiz("EnglishWord1Quiz")
        val CorrectEnglishWord2Quiz = dao.takeCorrectQuiz("EnglishWord2Quiz")
        val CorrectEnglishWord3Quiz = dao.takeCorrectQuiz("EnglishWord3Quiz")
        val CorrectEnglishWord4Quiz = dao.takeCorrectQuiz("EnglishWord4Quiz")
        val CorrectEnglishWord5Quiz = dao.takeCorrectQuiz("EnglishWord5Quiz")
        val CorrectEnglishWord6Quiz = dao.takeCorrectQuiz("EnglishWord6Quiz")
        val CorrectEnglishWord7Quiz = dao.takeCorrectQuiz("EnglishWord7Quiz")


        if (CorrectEnglishWord1Quiz.size == 10){
            binding.questionEitango1Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango1Button.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectEnglishWord2Quiz.size == 10){
            binding.questionEitango2Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango2Button.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectEnglishWord3Quiz.size == 10){
            binding.questionEitango3Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango3Button.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectEnglishWord4Quiz.size == 10){
            binding.questionEitango4Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango4Button.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectEnglishWord5Quiz.size == 10){
            binding.questionEitango5Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango5Button.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectEnglishWord6Quiz.size == 10){
            binding.questionEitango6Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango6Button.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectEnglishWord7Quiz.size == 10){
            binding.questionEitango7Button.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.questionEitango7Button.setBackgroundColor(Color.rgb(220,155,248))
        }


        //saveArray(isAllCorrectEnglish, "StringItem")


        //英単語①がタップされたとき
        binding.questionEitango1Button.setOnClickListener {
            //インテントを作る
            val questionIntent : Intent = Intent(this, QuestionActivity::class.java)
            //クイズをセットする
            questionIntent.putExtra("question", "EnglishWord1Quiz")
            questionIntent.putExtra("buttonName","questionEitango1Button")
            questionIntent.putExtra("Subject3", "English")
            //クイズ画面をスタートさせる
            startActivity(questionIntent)
        }

        //英単語②がタップされたとき
        binding.questionEitango2Button.setOnClickListener {
            //インテントを作る
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            //クイズをセットする
            questionIntent.putExtra("question", "EnglishWord2Quiz")
            questionIntent.putExtra("buttonName","questionEitango2Button")
            questionIntent.putExtra("Subject3", "English")
            //クイズ画面をスタートさせる
            startActivity(questionIntent)
        }

        //英単語③がタップされたとき
        binding.questionEitango3Button.setOnClickListener {
            //インテントを作る
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            //クイズをセットする
            questionIntent.putExtra("question", "EnglishWord3Quiz")
            questionIntent.putExtra("buttonName","questionEitango3Button")
            questionIntent.putExtra("Subject3", "English")
            //クイズ画面をスタートさせる
            startActivity(questionIntent)
        }

        //英単語④がタップされたとき
        binding.questionEitango4Button.setOnClickListener {
            //インテントを作る
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            //クイズをセットする
            questionIntent.putExtra("question", "EnglishWord4Quiz")
            questionIntent.putExtra("buttonName","questionEitango4Button")
            questionIntent.putExtra("Subject3", "English")
            //クイズ画面をスタートさせる
            startActivity(questionIntent)
        }

        //英単語⑤がタップされたとき
        binding.questionEitango5Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "EnglishWord5Quiz")
            questionIntent.putExtra("buttonName","questionEitango5Button")
            questionIntent.putExtra("Subject3", "English")
            startActivity(questionIntent)
        }

        //英単語⑥がタップされたとき
        binding.questionEitango6Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "EnglishWord6Quiz")
            questionIntent.putExtra("buttonName","questionEitango6Button")
            questionIntent.putExtra("Subject3", "English")
            startActivity(questionIntent)
        }

        //英単語⑦がタップされたとき
        binding.questionEitango7Button.setOnClickListener {
            val questionIntent: Intent = Intent(this, QuestionActivity::class.java)
            questionIntent.putExtra("question", "EnglishWord7Quiz")
            questionIntent.putExtra("buttonName","questionEitango7Button")
            questionIntent.putExtra("Subject3", "English")
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
                mistakesIntent.putExtra("subject","English")
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