package com.example.testapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

class PaymentRequest : AppCompatActivity() {
    private lateinit var spinner1 : Spinner
    private lateinit var spinner2 : Spinner
    private lateinit var submit : Button
    private lateinit var autotextView : AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_request)
//        autotextView = findViewById(R.id.autotextView);
        submit = findViewById(R.id.submit)
        submit.setOnClickListener{
            Toast.makeText(this@PaymentRequest , "Payment Submit Successfully✔✔" , Toast.LENGTH_SHORT).show()
            val intent = Intent( this@PaymentRequest , MainActivity::class.java)
            startActivity(intent)
        }

//        val userArr = ArrayList<String>();
//        for(i in 1..100){
//            userArr.add("User $i")
//        }

//        val adapter = ArrayAdapter(this,
//            android.R.layout.simple_list_item_1, userArr)
//        autotextView.setAdapter(adapter)


//        LedgerName.setOnClickListener(View.OnClickListener {
//            if (linerLayout1.visibility != View.VISIBLE) {
//                linerLayout1.visibility = View.VISIBLE
//                autotextView.setEnabled(true)
//                autotextView.onItemClickListener =
//                    AdapterView.OnItemClickListener { _, _, _, _ ->
//                        val name: String = autotextView.text.toString()
//                        LedgerName.text = name
//                        autotextView.setText("")
//                        autotextView.isEnabled = false
//                        linerLayout1.visibility = View.GONE
//                    }
//            } else {
//                linerLayout1.visibility = View.GONE
//            }
//        })


        val spinner1Items = listOf("Test User 1", "Test User 2")
        val spinner1Adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner1Items)
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1 = findViewById(R.id.spinner3)
        spinner1.adapter = spinner1Adapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        val spinner2Items = listOf("Test Sub User 1", "Test Sub User 2")
        val spinner2Adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner2Items)
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2 = findViewById(R.id.spinner4)
        spinner2.adapter = spinner2Adapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


}