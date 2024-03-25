package com.example.forloshkaryov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forloshkaryov.databinding.TodoTaskBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    val taskList = ArrayList<ToDoTask>()
    class TaskHolder(item : View) : RecyclerView.ViewHolder(item){
        val binding = TodoTaskBinding.bind(item)
        fun bind(task: ToDoTask) = with(binding){
            checkTask.isChecked = task.status;
            taskField.setText(task.task);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_task, parent, false)
        return TaskHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size;
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    fun addTask(tsk : ToDoTask){
        taskList.add(tsk)
        notifyDataSetChanged()
    }
}