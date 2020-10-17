package com.example.recycleview

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [producto::class], version = 1)
 abstract class database : RoomDatabase()
{
  abstract fun dao(): Daoproducto


  companion object {
    // Singleton prevents multiple instances of database opening at the
    // same time.
    @Volatile
    private var INSTANCE: database? = null

    fun getDatabase(context: Context): database {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized( this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          database::class.java,
          "bdproductos"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }

}