package app.kawamura.kako.orijinaru.ui.subject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.kawamura.kako.orijinaru.data.db.AppDatabase
import app.kawamura.kako.orijinaru.data.db.QuestionDao
import app.kawamura.kako.orijinaru.databinding.ActivitySelectSubjectBinding

class SelectSubjectActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivitySelectSubjectBinding
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
        binding = ActivitySelectSubjectBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        dao = AppDatabase.getInstance(this).questionDao()
        val CorrectJapanese = dao.takeCorrectSubjectQuiz("Japanese")!!
        val CorrectMath = dao.takeCorrectSubjectQuiz("Math")!!
        val CorrectScience = dao.takeCorrectSubjectQuiz("Science")!!
        val CorrectSocialStudies = dao.takeCorrectSubjectQuiz("SocialStudies")!!
        val CorrectEnglish = dao.takeCorrectSubjectQuiz("English")!!

        //val isAllCorrectSubject = getArray("StringItem12")!!

        if (CorrectEnglish.size == 70){
            binding.englishButton.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.englishButton.setBackgroundColor(Color.rgb(220,155,248))
        }
        if (CorrectJapanese.size == 40){
            binding.japaneseButton.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.japaneseButton.setBackgroundColor(Color.rgb(248,151,151))
        }
        if (CorrectMath.size == 30){
            binding.mathButton.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.mathButton.setBackgroundColor(Color.rgb(157,166,253))
        }
        if (CorrectScience.size == 20){
            binding.scienceButton.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.scienceButton.setBackgroundColor(Color.rgb(136,234,157))
        }
        if (CorrectSocialStudies.size == 10){
            binding.socialstudiesButton.setBackgroundColor(Color.rgb(255,144,84))
        }else{
            binding.socialstudiesButton.setBackgroundColor(Color.rgb(241,228,145))
        }
        //saveArray(isAllCorrectSubject, "StringItem11")

        //国語のボタンがタップされたとき
        binding.japaneseButton.setOnClickListener {
            val intent = Intent(this, SelectJapaneseActivity::class.java)
            startActivity(intent)
        }

        //数学のボタンがタップされたとき
        binding.mathButton.setOnClickListener {
            val intent = Intent(this, SelectMathActivity::class.java)
            startActivity(intent)
        }

        //理科のボタンがタップされたとき
        binding.scienceButton.setOnClickListener {
            val intent = Intent(this, SelectScienceActivity::class.java)
            startActivity(intent)
        }

        //社会のボタンがタップされたとき
        binding.socialstudiesButton.setOnClickListener {
            val intent = Intent(this, SelectSocialstudiesActivity::class.java)
            startActivity(intent)
        }

        //英語のボタンがタップされたとき
        binding.englishButton.setOnClickListener {
            val intent = Intent(this, SelectEnglishActivity::class.java)
            startActivity(intent)
        }
    }
}