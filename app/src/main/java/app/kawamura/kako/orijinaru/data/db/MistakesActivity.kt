package app.kawamura.kako.orijinaru.data.db

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import app.kawamura.kako.orijinaru.R
import app.kawamura.kako.orijinaru.databinding.ActivityMistakesBinding
import app.kawamura.kako.orijinaru.ui.ResultActivity

class MistakesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMistakesBinding
    private lateinit var dao: QuestionDao

    //正解の答えを入れる変数を作る
    var correctAnswer: String = ""
    //クイズ数をカウントする変数を作る
    var quizCount: Int = 0
    //正解の回数を入れる変数を作る
    var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMistakesBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        dao = AppDatabase.getInstance(this).questionDao()

        val subject = intent.getStringExtra("subject")
        var mistakeQuestionsList:List<Question>

        subject.let {mistakeQuestionsList = dao.takeMistakes(subject!!)}

        showQuestion(mistakeQuestionsList[0])

        //1つ目のボタンがタップされたら
        binding.answerButton1.setOnClickListener {
            checkAnswer(binding.answerButton1.text.toString(),mistakeQuestionsList[quizCount])
        }
        //２つ目のボタンがタップされたら
        binding.answerButton2.setOnClickListener {
            checkAnswer(binding.answerButton2.text.toString(),mistakeQuestionsList[quizCount])
        }
        //３つ目のボタンがタップされたら
        binding.answerButton3.setOnClickListener {
            checkAnswer(binding.answerButton3.text.toString(),mistakeQuestionsList[quizCount])
        }
        //４つ目のボタンがタップされたら
        binding.answerButton4.setOnClickListener {
            checkAnswer(binding.answerButton4.text.toString(),mistakeQuestionsList[quizCount])
        }

        //次に進むボタンがタップされたら
        binding.nextButton.setOnClickListener {
            //現在のクイズ数と全問クイズ数があっているか確認して
            if(quizCount == mistakeQuestionsList.size || quizCount == 10) {
                toResultActivity()
            } else {
                //判定画像を非表示にする
                binding.resultImage.isVisible = false
                binding.nextButton.isVisible = false
                //回答ボタンを有効にする
                binding.answerButton1.isEnabled = true
                binding.answerButton2.isEnabled = true
                binding.answerButton3.isEnabled = true
                binding.answerButton4.isEnabled = true
                //正解表示をリセットする
                binding.correctanswerText.text = " "
                //クイズを表示する
                nextQuestion(quizCount-1,mistakeQuestionsList)
            }
        }
    }

    fun toResultActivity(){
        binding.nextButton.isEnabled = false
        //結果画面へ移動する準備をする
        val mistakesResultIntent: Intent = Intent(this@MistakesActivity, MistakesResultActivity::class.java)
        //クイズ数をセットする
        mistakesResultIntent.putExtra("QuizCount", quizCount)
        //正解数をセットする
        mistakesResultIntent.putExtra("CorrectCount", correctCount)
        //resultIntent.putExtra("Subject5", subject4)
        //一秒待機
        Thread.sleep(1000)
        //結果画面に移動する
        startActivity(mistakesResultIntent)
    }

    fun showQuestion(question: Question) {
        binding.questionText.text = question.question
        val choices = listOf(
            question.answer1,
            question.answer2,
            question.answer3,
            question.answer4
        ).shuffled()
        binding.answerButton1.text = choices[0]
        binding.answerButton2.text = choices[1]
        binding.answerButton3.text = choices[2]
        binding.answerButton4.text = choices[3]
        correctAnswer = question.correctAnswer
    }

    fun checkAnswer(answerText: String, question: Question) {
        //タップした回答と正解を比べて
        question.isCorrect = (answerText == correctAnswer)
        if(answerText == correctAnswer) {
            //〇画像を表示する
            binding.resultImage.setImageResource(R.drawable.maru_image)
            //iscorrectをtrueにする
            dao.markAsCorrect(question.uid)
            //Log.d("true",question.toString())
            //正解数をカウントする
            correctCount += 1
        } else {
            //×画像を表示する
            binding.resultImage.setImageResource(R.drawable.batu_image)
            //iscorrectをfalseにする
            dao.markAsInCorrect(question.uid)
            //Log.d("false",question.toString())
        }
        //判定画像を表示する
        showAnswer()
        //クイズ数をカウントする
        quizCount += 1
    }

    fun showAnswer() {
        //正解を表示する
        binding.correctanswerText.text = "正解：$correctAnswer"
        //判定画像を表示する
        binding.resultImage.isVisible = true
        //次へボタンを表示する
        binding.nextButton.isVisible = true
        //回答ボタンを無効にする
        binding.answerButton1.isEnabled = false
        binding.answerButton2.isEnabled = false
        binding.answerButton3.isEnabled = false
        binding.answerButton4.isEnabled = false
    }

    fun nextQuestion(beforeQuestionId: Int,questionList: List<Question>) {
        val newQuestionId = beforeQuestionId+1
        val newQuestion = questionList[newQuestionId]
        showQuestion(newQuestion)
    }

}