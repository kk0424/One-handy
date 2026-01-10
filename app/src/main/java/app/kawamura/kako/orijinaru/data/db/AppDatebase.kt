package app.kawamura.kako.orijinaru.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import app.kawamura.kako.orijinaru.data.initializer.QuestionInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // AppDatabaseにDaoを追加
    abstract fun questionDao(): QuestionDao

    companion object {
        lateinit var INSTANCE: AppDatabase

        fun getInstance(context: Context): AppDatabase{
                synchronized(AppDatabase::class) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "database",
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            val prefs = context.getSharedPreferences("db_prefs", Context.MODE_PRIVATE)
                            val lastVersion = prefs.getInt("last_populate_version", 0)
                            val currentVersion = db.version  // SupportSQLiteDatabaseから直接取れる

                            if (lastVersion < currentVersion) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    INSTANCE.questionDao().deleteAll()
                                    QuestionInitializer.populateDatabase(context)
                                    prefs.edit().putInt("last_populate_version", currentVersion).apply()
                                }
                            }
                        }
                    })
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}