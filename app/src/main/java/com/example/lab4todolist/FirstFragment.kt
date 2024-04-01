package com.example.lab4todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4todolist.databinding.FragmentFirstBinding

var adaptator = TaskAdapter()
//var ffb: FragmentFirstBinding? = null
private lateinit var recyclerView: RecyclerView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val recyclerView:RecyclerView= _binding!!.rcView
        binding.apply { recyclerView.layoutManager = LinearLayoutManager(context); recyclerView.adapter = adaptator}
            //ffb = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonInfo.setOnClickListener{
            invokeThirdFrag();
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun invokeThirdFrag(){
        findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
    }
}