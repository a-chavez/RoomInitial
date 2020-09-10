package com.example.roominitial.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roominitial.database.TaskAPP

@Dao  // Obligatorio para indicar que es el DAO
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //En caso de estar duplicado que hacer
    suspend fun insertOne(obj: TaskAPP)                 // Se coloca suspend (corrutina)

    @Insert (onConflict = OnConflictStrategy.REPLACE) //Obligatorio cuando son muchos objetos a la vez
    fun insertLotOf (listObj: List<TaskAPP>)          //Varios Objetos

    @Update
    suspend fun updateOne(obj: TaskAPP)

    @Delete
    fun deleteOne (obj: TaskAPP)

    @Query("SELECT * FROM table_App ORDER BY id")
    fun getAllObj(): LiveData <List<TaskAPP>>  //     Antes de LiveData: fun getAllObj(): List<TaskAPP>

    @Query("SELECT * FROM table_App WHERE id=:mId")
    fun getOneObj(mId: Int): LiveData<TaskAPP>

    @Query("DELETE FROM table_App")
    suspend fun deleteAll()  //TaskAPP

}