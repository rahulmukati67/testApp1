package com.example.testapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class MeterReading : Fragment() {
    private  lateinit var ColleagueName: TextView
    private  lateinit var spinner2 :Spinner
    private lateinit var text:TextView
    private  lateinit var linerLayout1 : LinearLayout
    private lateinit var show_meter_reading : TextView
    private lateinit var notes :EditText
    private  lateinit var btn:Button
    private lateinit var txtMeterReading: EditText
    private lateinit var autoCompleteTextView : AutoCompleteTextView
    private var linearLayoutListener: LinearLayoutListener? = null

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        try{
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
    interface LinearLayoutListener {
        fun hideLinearLayout()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_meter_reading, container, false)
        linerLayout1 = rootView.findViewById(R.id.linerLayout1);

        rootView.setOnTouchListener { _, event ->

            if (linerLayout1.visibility == View.VISIBLE) {
                linerLayout1.visibility = View.GONE
                return@setOnTouchListener true
            }
            false
        }
        return rootView
    }

    fun setLinearLayoutListener(listener: LinearLayoutListener) {
        this.linearLayoutListener = listener
    }

    fun notifyLinearLayoutHidden() {
        linearLayoutListener?.hideLinearLayout()
    }

//    fun hideLinearLayout() {
//        val linearLayout = view?.findViewById<LinearLayout>(R.id.linerLayout1)
//        if (linearLayout != null) {
//            if(linearLayout.visibility == View.VISIBLE) {
//                linearLayout.visibility = View.GONE
//                Log.d("MainActivity", "LinearLayout visibility set to GONE")
//            }
//        }
//    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         btn =  view.findViewById(R.id.button)
         notes = view.findViewById(R.id.notes)
         ColleagueName= view.findViewById(R.id.ColleagueName);
         txtMeterReading= view.findViewById(R.id.txtMeterReading);
         show_meter_reading = view.findViewById(R.id.show_meter_reading)
         autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
         btn.setOnClickListener {
             galleryLauncher.launch("image/*")
             text = view.findViewById(R.id.file)
             text.text = "File Selected"
             if (linerLayout1.visibility == View.VISIBLE) {
                 linerLayout1.visibility = View.GONE
             }
         }
//        val spinner1Items = listOf("Test User 1", "Test User 2")
        val spinner2Items = listOf("Type 1", "type 2" , "type 3")
        val userArr = ArrayList<String>();

       for(i in 1..100){
           userArr.add("User $i")
       }


//        val spinner1Adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinner1Items)
//        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner1 = view.findViewById(R.id.spinner1)
//        spinner1.adapter = spinner1Adapter
//        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            userArr
        )
        autoCompleteTextView.setAdapter(adapter)

        ColleagueName.setOnClickListener(View.OnClickListener {
            if (linerLayout1.visibility != View.VISIBLE) {
                linerLayout1.visibility = View.VISIBLE
                autoCompleteTextView.setEnabled(true)
                autoCompleteTextView.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, _, _ ->
                        val name: String = autoCompleteTextView.text.toString()
                        ColleagueName.text = name
                        autoCompleteTextView.setText("")
                        autoCompleteTextView.isEnabled = false
                        linerLayout1.visibility = View.GONE
                    }
        } else {
            linerLayout1.visibility = View.GONE
        }
        })

        val spinner2Adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinner2Items)
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2 = view.findViewById(R.id.spinner2)
        spinner2.adapter = spinner2Adapter

        spinner2.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (linerLayout1.visibility == View.VISIBLE) {
                    linerLayout1.visibility = View.GONE
                }
            }
            false // Let the touch event propagate
        }

        txtMeterReading.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (linerLayout1.visibility == View.VISIBLE) {
                    linerLayout1.visibility = View.GONE
                }
            }
            false
        }

        notes.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                if (linerLayout1.visibility == View.VISIBLE) {
                    linerLayout1.visibility = View.GONE
                }
            }
            false
        }

        show_meter_reading.setOnClickListener {
            if (linerLayout1.visibility == View.VISIBLE) {
                linerLayout1.visibility = View.GONE
            }
        }
    }
}
