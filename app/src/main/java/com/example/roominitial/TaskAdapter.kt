package com.example.roominitial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roominitial.database.TaskAPP
import kotlinx.android.synthetic.main.task_item_list.view.*

class TaskAdapter (var passThedata: PassThedata) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {  //falta el onclick

    private var datalist = emptyList<TaskAPP>()

    //Funcion que actualiza el listado del adapter
    fun updateList(mDataList: List<TaskAPP>){
        datalist = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val idtext      = itemView.tvID
        val taskText    = itemView.tvTask
        val endText     = itemView.tvEnd
        val chkTask     = itemView.checkBox
        val itemView    = itemView.setOnClickListener (this)

        override fun onClick(p0: View?) {
            passThedata.passData(datalist[adapterPosition])

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.task_item_list,
            parent,
            false
        )
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
    val mTask : TaskAPP = datalist[position]
        holder.idtext.text = mTask.id.toString()
        holder.taskText.text = mTask.task
        holder.endText.text = mTask.datatime  //implementar fecha
        holder.chkTask.isChecked = mTask.status
    }

    override fun getItemCount() = datalist.size

    interface PassThedata{ //paso 1 para pasar la data
        fun passData (task: TaskAPP)
    }

}