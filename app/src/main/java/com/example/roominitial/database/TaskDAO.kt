package com.example.roominitial.database

import androidx.room.*
import com.example.roominitial.database.TaskAPP

@Dao  // Obligatorio para indicar que es el DAO
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //En caso de estar duplicado que hacer
    fun insertOne(obj: TaskAPP)

    @Insert (onConflict = OnConflictStrategy.REPLACE) //Obligatorio cuando son muchos objetos a la vez
    fun insertLotOf (listObj: List<TaskAPP>)          //Varios Objetos

    @Update
    fun updateOne(obj: TaskAPP)

    @Delete
    fun deleteOne (obj: TaskAPP)

    @Query("SELECT * FROM table_App")
    fun getAllObj(): List<TaskAPP>

    @Query("SELECT * FROM table_App WHERE id=:mId")
    fun getOneObj(mId: Int): TaskAPP
}