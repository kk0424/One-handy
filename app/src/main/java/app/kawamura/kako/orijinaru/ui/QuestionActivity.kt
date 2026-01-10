package app.kawamura.kako.orijinaru.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.room.Query
import androidx.room.Update
import app.kawamura.kako.orijinaru.R
import app.kawamura.kako.orijinaru.databinding.ActivityQuestionBinding
import app.kawamura.kako.orijinaru.data.db.AppDatabase
import app.kawamura.kako.orijinaru.data.db.Question
import app.kawamura.kako.orijinaru.data.db.QuestionDao


class QuestionActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivityQuestionBinding
    private lateinit var timer: CountDownTimer
    private lateinit var buttonName: String
    //private lateinit var subject4: String
    private lateinit var dao: QuestionDao

    //クイズ数をカウントする変数を作る
    var quizCount: Int = 0
    //正解の回数を入れる変数を作る
    var correctCount: Int = 0
    //正解の答えを入れる変数を作る
    var correctAnswer: String = ""
    //タイムをカウントする変数を作る
    var timeCount = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        timer = object:CountDownTimer(100000,100){
            override fun onTick(p0:Long) {
                timeCount += 0.1
                binding.timeText.text = ("%.1f".format(timeCount))
            }
            override fun onFinish() {
                binding.questionText.text = "時間切れだよ！"
                binding.answerButton1.isEnabled = false
                binding.answerButton2.isEnabled = false
                binding.answerButton3.isEnabled = false
                binding.answerButton4.isEnabled = false
                timer.cancel()
                toResultActivity()
            }
        }.start()

        dao = AppDatabase.getInstance(this).questionDao()


        //単元選択画面からクイズを受け取る
        val questionName = intent.getStringExtra("question")!!
        buttonName = intent.getStringExtra("buttonName")!!
        //subject4 = intent.getStringExtra("Subject3")!!


        //クイズを受け取る
        val questionList = dao.getQuestionsByGroup(questionName)
        //Log.d("debug",questionList.toString())
        //Log.d("debug",questionName.toString())

        //クイズを表示する
        showQuestion(questionList[0])

        //1つ目のボタンがタップされたら
        binding.answerButton1.setOnClickListener {
            checkAnswer(binding.answerButton1.text.toString(),questionList[quizCount])
        }
        //２つ目のボタンがタップされたら
        binding.answerButton2.setOnClickListener {
            checkAnswer(binding.answerButton2.text.toString(),questionList[quizCount])
        }
        //３つ目のボタンがタップされたら
        binding.answerButton3.setOnClickListener {
            checkAnswer(binding.answerButton3.text.toString(),questionList[quizCount])
        }
        //４つ目のボタンがタップされたら
        binding.answerButton4.setOnClickListener {
            checkAnswer(binding.answerButton4.text.toString(),questionList[quizCount])
        }

        //次に進むボタンがタップされたら
        binding.nextButton.setOnClickListener {
            //現在のクイズ数と全問クイズ数があっているか確認して
            if(quizCount == questionList.size) {
                timer.cancel()
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
                binding.correctanswerText.isVisible = false
                //クイズを表示する
                nextQuestion(quizCount-1,questionList)
            }
        }

    }

    fun toResultActivity(){
        Log.d("toResultActivity","result")
        binding.nextButton.isEnabled = false
        //結果画面へ移動する準備をする
        val resultIntent: Intent = Intent(this@QuestionActivity, ResultActivity::class.java)
        //クイズ数をセットする
        resultIntent.putExtra("QuizCount", quizCount)
        //正解数をセットする
        resultIntent.putExtra("CorrectCount", correctCount)
        //タイムをセットする
        resultIntent.putExtra("TimeCount", timeCount)
        resultIntent.putExtra("buttonName", buttonName)
        //resultIntent.putExtra("Subject5", subject4)
        //一秒待機
        //結果画面に移動する
        startActivity(resultIntent)
    }

    //画面にクイズを表示させる関数
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

    //回答をチェックする関数
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

    //判定画像を表示する際のボタンの設定をする関数
    fun showAnswer() {
        //正解を表示する
        binding.correctanswerText.text = "正解：$correctAnswer"
        binding.correctanswerText.isVisible = true
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