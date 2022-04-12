package com.demo.test.kotlin.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.demo.test.kotlin.R

/**
 * 二级菜单
 * Created by LaiYingtang on 2016/5/25.
 */
open class SecondMenuDialog(context: Context?) : Dialog(context!!, R.style.dialog_change_card) {
    var mContext:Context? = null
    private var containerViewGroup: LinearLayout
    var mContentView: View? = null
    private var titleView: TextView? = null
    var mWindow: Window? = null

    //构造器
    init {
        this.mContext = context
        containerViewGroup = layoutInflater.inflate(R.layout.second_menu_dialog, null) as LinearLayout
        titleView = containerViewGroup.findViewById<View>(R.id.dictdialog_title_tv) as TextView
    }

    override fun <T : View?> findViewById(id: Int): T {
        return mContentView!!.findViewById(id)
    }

    /**
     * 设置窗口显示
     */
    private fun windowDeploy() {
        mWindow = getWindow() // 得到对话框
        mWindow!!.setWindowAnimations(R.style.RegDialogAnimation) // 设置窗口弹出动画效果


//        val windowAttributes = mWindow!!.attributes
//        windowAttributes.x = 0 // x小于0左移，大于0右移
//        windowAttributes.y = 0 // y小于0上移，大于0下移
//        windowAttributes.height = 2 * mContext!!.resources.displayMetrics.heightPixels / 3
//        windowAttributes.width = LinearLayout.LayoutParams.FILL_PARENT
//        windowAttributes.alpha = 0.6f //设置透明度
//        windowAttributes.gravity = Gravity.BOTTOM // 设置重力，对齐方式
//        mWindow!!.attributes = windowAttributes

        val windowAttributes = mWindow!!.attributes.apply {
            x = 0 // x小于0左移，大于0右移
            y = 0 // y小于0上移，大于0下移
            height = 2 * mContext!!.resources.displayMetrics.heightPixels / 3
            width = LinearLayout.LayoutParams.FILL_PARENT
            alpha = 0.6f //设置透明度
            gravity = Gravity.BOTTOM // 设置重力，对齐方式
        }
        mWindow!!.attributes = windowAttributes
    }

    //显示到layout里面
    override fun show() {

        mContentView?.let {
            containerViewGroup.addView(mContentView)
        }

//        if (mContentView != null) {
//            containerViewGroup.addView(mContentView)
//        }

        setContentView(containerViewGroup)
        setCanceledOnTouchOutside(true)
        windowDeploy()
        super.show()
    }

    //选中的title设置为title
    override fun setTitle(title: CharSequence?) {
        if (titleView != null) titleView!!.text = title
    }
}