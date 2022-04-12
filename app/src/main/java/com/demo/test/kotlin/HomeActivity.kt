package com.demo.test.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.demo.test.kotlin.bean.MenuData
import com.demo.test.kotlin.dialog.ThreeMenuDialog
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*


class HomeActivity : Activity() {

    companion object {
        private const val REQUEST_CODE = 100 //请求码
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //第一个选择
        textview1.setOnClickListener {
            startActivityForResult(
                Intent(this@HomeActivity, MainActivity::class.java),
                REQUEST_CODE
            )
        }
        //第二个
        textview2.setOnClickListener {
            val dialog = ThreeMenuDialog(this@HomeActivity)
            dialog!!.setOnItemClickListener(object : ThreeMenuDialog.MenuItemClickListener {
                override fun onMenuItemClick(menuData: MenuData?) {
                    if (menuData != null)
                        textview2.text = menuData.name //选中第三个菜单后，主页面的name设置为选中的name
                }
            })
            dialog.show()
        }
    }

    //跳转回掉
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> if (data != null) {
                val menuData: MenuData = data.getSerializableExtra("menu") as MenuData
                if (menuData != null)
                    textview1.text = menuData.name
            }
        }
    }
}
