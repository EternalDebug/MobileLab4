package com.example.lab4todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.example.lab4todolist.databinding.FragmentSecondBinding
import com.example.lab4todolist.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        //_binding!!.buttonAdd.setOnClickListener{ v -> addNewToDO(v)}
        var name = "Наименование задачи: ";
        var info = "Детальная информация о задаче: ";
        var ch = "Выполнена ли задача: ";
        /*if (CuPoRos < tdViewModel!!.notes.value!!.size && tdViewModel!!.notes.value!!.size != 0)
        {
            name += tdViewModel!!.notes.value!![CuPoRos].task;
            info += tdViewModel!!.notes.value!![CuPoRos].info;
            ch += tdViewModel!!.notes.value!![CuPoRos].status.toString();
        }*/

        if (CuPoRos < Repka.getAll().size && Repka.getAll().size != 0)
        {
            name += Repka.getAll()[CuPoRos].task;
            info += Repka.getAll()[CuPoRos].info;
            ch += Repka.getAll()[CuPoRos].status.toString();
        }

        binding.textView2.text = name;
        binding.textView3.text = info;
        binding.textView4.text = ch;
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonToList.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}