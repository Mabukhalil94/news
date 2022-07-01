package com.example.newsapp.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.models.Article


@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao

    companion object {
        @Volatile
        private var instance : ArticleDatabase? = null
        private val LOCK = Any() //////What is Lock uses for?

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){/////////Why invoke?, what is use for?
            instance ?: createDatabase(context).also { instance = it }////Search about also and it what they are using for
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}