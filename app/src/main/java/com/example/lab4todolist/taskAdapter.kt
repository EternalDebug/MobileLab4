package com.example.lab4todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4todolist.databinding.TodoTaskBinding

var CuPoRos: Int = 0;
val taskList = ArrayList<ToDoTask>()

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {


    class TaskHolder(item: View, lam: (Int) -> Unit) : RecyclerView.ViewHolder(item) {
        val binding = TodoTaskBinding.bind(item)
        fun bind(task: ToDoTask, pos: Int, param: (Any) -> Unit) = with(binding) {
            checkTask.isChecked = task.status;
            taskField.setText(task.task);

            val txt = itemView.findViewById<TextView>(R.id.taskField)
            txt.setOnClickListener {
                CuPoRos = pos;

                //findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
            }
            val btn = itemView.findViewById<Button>(R.id.buttonDel);
            btn.setOnClickListener(param)

            val chbox = itemView.findViewById<CheckBox>(R.id.checkTask)
            chbox.setOnClickListener{
                taskList[pos].status = !taskList[pos].status;

            }

            //taskField.paintFlags = taskField.paintFlags or STRIKE_THRU_TEXT_FLAG;
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_task, parent, false)
        return TaskHolder(view, {})
    }

    override fun getItemCount(): Int {
        return taskList.size;
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position], position) { i -> removeTask(position) }
    }

    fun addTask(tsk: ToDoTask) {
        taskList.add(tsk)
        notifyDataSetChanged()
    }

    fun removeTask(position: Int) {
        taskList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addTaskAnotherFrag(tsk: ToDoTask) {
        taskList.add(tsk)
    }
}

