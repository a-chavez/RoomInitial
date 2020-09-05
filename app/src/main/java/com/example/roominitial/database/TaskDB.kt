package com.example.roominitial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME = "task_db"            // puede cambiar

@Database (entities = [TaskAPP::class], version = 1) //Entre parentesis las [entidades] que la van a usar y la version // puede cambiar
abstract class TaskDB: RoomDatabase() {
    //aca van los DAO
    abstract fun getObjDao(): TaskDAO   //aca va la interface // puede cambiar

    companion object {
        @Volatile
        private var INSTANCE: TaskDB? = null         //Tiene el nombre de esta misma clase por ejemplo TaskDB

        fun getDataBase(context: Context): TaskDB {
            val tempInstance = INSTANCE
            if(tempInstance !=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, TaskDB::class.java, DATA_BASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}