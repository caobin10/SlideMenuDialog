package com.ufi.pdioms.ztkotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerView_comple.initFragment(savedInstanceState)

        btn_main.setOnClickListener { myDrawer.openDrawer(GravityCompat.START)}


    }

    override fun onDestroy() {
        super.onDestroy()
        drawerView_comple.onDestroy()
    }
}
