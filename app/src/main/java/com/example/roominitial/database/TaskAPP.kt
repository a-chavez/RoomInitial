package com.example.roominitial.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "table_App") // Forzar el nombre de la tabla
data class TaskAPP(
    @PrimaryKey (autoGenerate = true) //Parte del Primary KEY
    @NonNull                          //Parte del Primary KEY
    val id: Int =0,
    val task:String,
    val status:Boolean,
    val datatime: String )