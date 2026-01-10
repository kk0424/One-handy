package app.kawamura.kako.orijinaru.data.initializer

    import android.content.Context
    import android.util.Log
    import app.kawamura.kako.orijinaru.data.db.AppDatabase
    import app.kawamura.kako.orijinaru.data.db.mathQuestionList
    import app.kawamura.kako.orijinaru.data.db.japaneseQuestionList
    import app.kawamura.kako.orijinaru.data.db.scienceQuestionList
    import app.kawamura.kako.orijinaru.data.db.socialStudiesQuestionList
    import kotlinx.coroutines.CoroutineScope
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch

    object QuestionInitializer {
        fun populateDatabase(context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                val dao = AppDatabase.getInstance(context).questionDao()
                japaneseQuestionList.forEach { dao.insert(it) }
                mathQuestionList.forEach { dao.insert(it) }
                scienceQuestionList.forEach { dao.insert(it) }
                socialStudiesQuestionList.forEach { dao.insert(it) }
                Log.d("database","database initialized")
            }
        }

    }