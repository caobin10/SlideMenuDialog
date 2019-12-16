package com.ufi.pdioms.ztkotlin


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
Created by wrs on 16/12/2019,下午 4:54
projectName: ZKotlin
packageName: com.example.admin.zkotlin
 */

public class TaskMgrFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_mgr, null)
    }

}