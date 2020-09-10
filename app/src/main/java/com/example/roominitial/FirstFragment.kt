package com.example.roominitial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roominitial.database.TaskAPP
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), TaskAdapter.PassThedata {

    lateinit var viewModel: TaskViewModel  //lateinit permites instanciar algo sin que de error porque aun no se inicializa
    var txt: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
       // data()



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = recyclerView
        val mAdapter = TaskAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        fab.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel.mAllTask.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })


        }

    override fun passData(task: TaskAPP) {
  //  Toast.makeText(context, task.task, Toast.LENGTH_SHORT).show()
        val b = Bundle()
        b.putInt("ID",task.id)
        b.putString("Task",task.task)
        b.putString("Termino",task.datatime)
        b.putBoolean("Check",task.status)

        Toast.makeText(context,task.id.toString(),Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,b)

    }


}