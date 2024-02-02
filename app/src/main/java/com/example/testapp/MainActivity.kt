package com.example.testapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
      private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout  = findViewById(R.id.TabLayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.text != null && tab.text.toString() == "Meter Reading") {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MeterReading())
                        .commit()
                } else if(tab.text != null && tab.text.toString() == "Attendance") {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Attendence())
                        .commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, Attendence())
                    .commit()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        }
}