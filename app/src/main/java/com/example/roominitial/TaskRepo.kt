package com.example.roominitial

import com.example.roominitial.database.TaskAPP
import com.example.roominitial.database.TaskDAO

class TaskRepo (private val mTaskDAO: TaskDAO){

    val mListAllObj : List<TaskAPP> = mTaskDAO.getAllObj()

    fun insertObj(mObj: TaskAPP){
        mTaskDAO.insertOne(mObj)
    }


}