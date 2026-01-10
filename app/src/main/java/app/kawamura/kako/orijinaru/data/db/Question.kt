package app.kawamura.kako.orijinaru.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "subject")
    var subject: String,
    @ColumnInfo(name = "questionGroup")
    var questionGroup: String,
    @ColumnInfo(name = "question")
    var question: String,
    @ColumnInfo(name = "answer1")
    var answer1: String,
    @ColumnInfo(name = "answer2")
    var answer2: String,
    @ColumnInfo(name = "answer3")
    var answer3: String,
    @ColumnInfo(name = "answer4")
    var answer4: String,
    @ColumnInfo(name = "correctAnswer")
    var correctAnswer: String,
    @ColumnInfo(name = "is_correct")
    var isCorrect: Boolean?,
)
