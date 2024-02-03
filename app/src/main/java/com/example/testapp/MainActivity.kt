package com.example.testapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() , MeterReading.LinearLayoutListener {
      private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rootView = findViewById<View>(R.id.mainActivity)

        rootView.setOnTouchListener { _, event ->
            Log.d("MainActivity", "LinearLayout visibility set to GONE")
            supportFragmentManager.fragments.forEach { fragment ->
                if (fragment is MeterReading) {
                    fragment.setLinearLayoutListener(this)
                    fragment.notifyLinearLayoutHidden()
                }
            }
            false // Let the touch event propagate
        }

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
    override fun hideLinearLayout() {
        val linearLayout = findViewById<LinearLayout>(R.id.linerLayout1)
        if(linearLayout.visibility == View.VISIBLE) {
            linearLayout.visibility = View.GONE
        }
    }
}