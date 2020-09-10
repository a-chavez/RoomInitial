package com.example.roominitial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roominitial.database.TaskAPP
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.task_item_list.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    //variables de campo
    lateinit var mViewModel: TaskViewModel
    var bID : Int? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel= ViewModelProvider(this).get(TaskViewModel::class.java)
        arguments?.let {
            bID = it.getInt("ID")
            Log.d("Atencion",bID.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bID?.let{
            mViewModel.getOneTaskByID(it).observe(viewLifecycleOwner, Observer {
                Log.d("Atencion", it.task)
                etTask.setText(it.task)
                etEnd.setText(it.datatime)
               //checkBox.isChecked = it.status
            })
        }

        button_second.setOnClickListener{
            val textTask = etTask.text.toString()
            val endTask  = etEnd.text.toString()
            val checkBox = checkBox2.isChecked

            if (textTask.isNotEmpty()) {
                if (bID != null) {
                    val mTask = TaskAPP(task = textTask, status = checkBox, datatime = endTask, id = bID!!)
                    mViewModel.updateTask(mTask)
                } else {
                    val mTask = TaskAPP(task = textTask, status = checkBox, datatime = endTask)
                    mViewModel.insertTask(mTask)
                }
            }
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}