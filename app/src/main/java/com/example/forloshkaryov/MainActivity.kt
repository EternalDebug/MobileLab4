package com.example.forloshkaryov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forloshkaryov.databinding.ActivityMainBinding
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private var adaptator = TaskAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adaptator.addTask(ToDoTask(false, "Что-то"))
        adaptator.addTask(ToDoTask(false, "как-то"))
        adaptator.addTask(ToDoTask(true, ""))
        adaptator.addTask(ToDoTask(false, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        adaptator.addTask(ToDoTask(true, "пусто"))
        init();
    }

    public fun doIt(view: View) {

    }

    private fun init(){
        binding.apply { rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adaptator}
    }
}