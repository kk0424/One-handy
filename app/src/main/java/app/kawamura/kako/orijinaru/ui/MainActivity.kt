package app.kawamura.kako.orijinaru.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.kawamura.kako.orijinaru.databinding.ActivityMainBinding
import app.kawamura.kako.orijinaru.ui.subject.SelectSubjectActivity

class MainActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }


        //STARTボタンがタップされたとき
        binding.startButton.setOnClickListener {
            val intent = Intent(this, SelectSubjectActivity::class.java)
            startActivity(intent)
        }
    }
}