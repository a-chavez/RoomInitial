package com.example.roominitial

import androidx.lifecycle.LiveData
import com.example.roominitial.database.TaskAPP
import com.example.roominitial.database.TaskDAO

class TaskRepo (private val mTaskDAO: TaskDAO){

    val mListAllObj : LiveData<List<TaskAPP>> = mTaskDAO.getAllObj()  //listado

    suspend fun insertObj(mObj: TaskAPP){
        mTaskDAO.insertOne(mObj)
    }

    suspend fun deleteAll(){
        mTaskDAO.deleteAll()
    }

}