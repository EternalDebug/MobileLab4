package com.example.lab4todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4todolist.databinding.TodoTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var CuPoRos: Int = 0;
//val taskList = tdViewModel!!.notes//ArrayList<ToDoTask>()

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {


    class TaskHolder(item: View, lam: (Int) -> Unit) : RecyclerView.ViewHolder(item) {
        val binding = TodoTaskBinding.bind(item)
        fun bind(task: TaskDB, pos: Int, param: (Any) -> Unit) = with(binding) {
            checkTask.isChecked = task.status!!;
            taskField.setText(task.task);

            val txt = itemView.findViewById<TextView>(R.id.taskField)
            txt.setOnClickListener {
                CuPoRos = pos;

                txt.findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
            }
            val btn = itemView.findViewById<Button>(R.id.buttonDel);
            btn.setOnClickListener(param)

            val chbox = itemView.findViewById<CheckBox>(R.id.checkTask)
            chbox.setOnClickListener{
                //taskList.[pos].status = !taskList[pos].status;
                //tdViewModel!!.update(task);
                task.status = !task.status!!
                CoroutineScope(Dispatchers.IO).launch{
                    Repka.update(task);
                }

            }

            //taskField.paintFlags = taskField.paintFlags or STRIKE_THRU_TEXT_FLAG;
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_task, parent, false)
        return TaskHolder(view, {})
    }

    override fun getItemCount(): Int {
        /*if (tdViewModel!!.notes.value == null) {
            return 0
        }
        else//tdViewModel!!.notes.value!!.size;
        {
            return tdViewModel!!.notes.value!!.size;
        }*/
        val res = Repka.getAll()
        return res.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        //holder.bind(tdViewModel!!.notes.value!![position], position) { i -> removeTask(position) }
        holder.bind(Repka.getAll()[position], position) { i -> removeTask(position) }
    }

    fun addTask(tsk: TaskDB) {
        //taskList.add(tsk)
        //tdViewModel!!.insert(tsk)
        CoroutineScope(Dispatchers.IO).launch{
        Repka.insert(tsk)}
        notifyDataSetChanged()
    }

    fun removeTask(position: Int) {
        //taskList.removeAt(position)
        CoroutineScope(Dispatchers.IO).launch{
            val tsk = Repka.getAll()[position]
            Repka.delete(tsk)
        }
        //val tsk = tdViewModel!!.notes.value!![position]
        //tdViewModel!!.delete(tsk)
        notifyDataSetChanged()
    }

}

