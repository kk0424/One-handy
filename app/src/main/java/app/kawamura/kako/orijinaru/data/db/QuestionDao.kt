package app.kawamura.kako.orijinaru.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDao {
    // データを追加
    @Insert
    fun insert(question: Question)

    // データを更新
    @Update
    fun update(question: Question)

    // データを削除
    @Delete
    fun delete(question: Question)

    // 全てのデータを取得
    @Query("select * from questions")
    fun getAll(): List<Question>

    // 全てのデータを削除
    @Query("delete from questions")
    fun deleteAll()

    // UserのuidがidのUserを取得
    @Query("select * from questions where questionGroup = :group")
    fun getQuestionsByGroup(group: String): List<Question>

    @Query("update questions set is_correct = true where uid = :uid")
    fun markAsCorrect(uid:Int)

    @Query("update questions set is_correct = false where uid = :uid")
    fun markAsInCorrect(uid:Int)

    @Query("select * from questions where subject = :group and is_correct = false")
    fun takeMistakes(group: String): List<Question>

    @Query("select * from questions where questionGroup = :group and is_correct = true")
    fun takeCorrectQuiz(group: String): List<Question>

    @Query("select * from questions where subject = :group and is_correct = true")
    fun takeCorrectSubjectQuiz(group: String): List<Question>
}
