package com.example.roominitial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roominitial.database.TaskAPP
import com.example.roominitial.database.TaskDAO
import com.example.roominitial.database.TaskDB
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {   //Porque se requiere el contexto y asi se pre-establece y no se "amarra a nada"

    private val mRepository: TaskRepo       //representacion del repositorio solo aca adentro (por eso es private)
    val mAllTask: LiveData<List<TaskAPP>>


    init{        // palabra reservada para inicializar cosas
        val mTaskDao = TaskDB.getDataBase(application).getObjDao()
        mRepository = TaskRepo(mTaskDao)
        mAllTask = mRepository.mListAllObj
    }

    fun insertTask (mTask: TaskAPP) = viewModelScope.launch {    // Asi se implementa la corrutina
        mRepository.insertObj(mTask)
    }

    fun deleteAll () = viewModelScope.launch {    // Asi se implementa la corrutina
        mRepository.deleteAll()
    }

    fun getOneTaskByID(id:Int) : LiveData<TaskAPP> {
        return mRepository.getOneObjbyID(id)
    }

    //Este metodo hace update sin suspended
    fun updateTask(mTask: TaskAPP) = viewModelScope.launch {
        mRepository.updateTask(mTask)
    }

}