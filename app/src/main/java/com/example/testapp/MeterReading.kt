package com.example.testapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.findViewTreeViewModelStoreOwner
class MeterReading : Fragment() {
    private  lateinit var spinner1: Spinner
    private  lateinit var spinner2 :Spinner
    private lateinit var text:TextView
    private lateinit var show_meter_reading : TextView
    private  lateinit var btn:Button
    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        try{
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meter_reading, container, false)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         btn =  view.findViewById(R.id.button)
         show_meter_reading = view.findViewById(R.id.show_meter_reading)
         btn.setOnClickListener {
             galleryLauncher.launch("image/*")
             text = view.findViewById(R.id.file)
             text.text = "File Selected"
         }
        val spinner1Items = listOf("Test User 1", "Test User 2")
        val spinner2Items = listOf("Type 1", "type 2" , "type 3")
        val spinner1Adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinner1Items)
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1 = view.findViewById(R.id.spinner1)
        spinner1.adapter = spinner1Adapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        val spinner2Adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinner2Items)
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2 = view.findViewById(R.id.spinner2)
        spinner2.adapter = spinner2Adapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}
