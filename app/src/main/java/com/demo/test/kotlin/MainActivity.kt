package com.demo.test.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.demo.test.kotlin.adapter.MenuDialogAdapter
import com.demo.test.kotlin.adapter.MyPagerAdapter
import com.demo.test.kotlin.bean.MenuData
import com.demo.test.kotlin.utils.MenuDataManager
import java.io.Serializable
import java.util.*
import kotlinx.android.synthetic.main.activity_main.viewpager
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private var mContext: Context? = null
    var menuDataManager = MenuDataManager.instance

    //    private var mViewPager: MyViewPager? = null
//    private var view1: View? = null
//    private var view2: View? = null
//    private var view3: View? = null
//    private var mListView1: ListView? = null
//    private var mListView2: ListView? = null
//    private var mListView3: ListView? = null
    private var mListView1Adapter: MenuDialogAdapter? = null
    private var mListView2Adapter: MenuDialogAdapter? = null
    private var mListView3Adapter: MenuDialogAdapter? = null
    private val views: MutableList<View?> = ArrayList()
    private val resultDate: MenuData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        initViews()
    }

    //操作控件
    private fun initViews() {
        //一级
//        mViewPager = findViewById<View>(R.id.viewpager) as MyViewPager
        val inflater = LayoutInflater.from(this)

        val view1 = inflater.inflate(R.layout.pager_number, null)
        val view2 = inflater.inflate(R.layout.pager_number, null)
        val view3 = inflater.inflate(R.layout.pager_number, null)

        val mListView1 = view1.findViewById<View>(R.id.listview) as ListView
        val mListView2 = view2.findViewById<View>(R.id.listview) as ListView
        val mListView3 = view3.findViewById<View>(R.id.listview) as ListView

        val list1 = menuDataManager!!.getTripleColumnData(this, 0)

        mListView1Adapter = MenuDialogAdapter(this, list1)
        mListView1Adapter!!.setSelectedBackgroundResource(R.drawable.select_white) //选中时
        mListView1Adapter!!.setHasDivider(false)
        mListView1Adapter!!.setNormalBackgroundResource(R.color.menudialog_bg_gray) //未选中
        mListView1.adapter = mListView1Adapter

        views.add(view1)
        views.add(view2) //加载了一二级菜单
        viewpager.adapter = MyPagerAdapter(views)
        mListView1.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            mListView1Adapter?.let { mListView1Adapter!!.setSelectedPos(position) }

//            if (mListView1Adapter != null)
//                mListView1Adapter!!.setSelectedPos(position)

            mListView2Adapter?.let { mListView2Adapter!!.setSelectedPos(-1) }

//            if (mListView2Adapter != null)
//                mListView2Adapter!!.setSelectedPos(-1)

            if (views.contains(view3)) {
                views.remove(view3)
                viewpager.adapter!!.notifyDataSetChanged()
            }
            val menuData = parent.getItemAtPosition(position) as MenuData
            if (menuData.id == 0) { //不限

                mListView2Adapter?.let {
                    mListView2Adapter!!.setData(ArrayList())
                    mListView2Adapter!!.notifyDataSetChanged()
                }

//                if (mListView2Adapter != null) {
//                    mListView2Adapter!!.setData(ArrayList())
//                    mListView2Adapter!!.notifyDataSetChanged()
//                }

                setResultDate(menuData)
            } else {
                val list2 = menuDataManager!!.getTripleColumnData(mContext, menuData.id)
                if (mListView2Adapter == null) {
                    mListView2Adapter = MenuDialogAdapter(mContext, list2)
                    mListView2Adapter!!.setNormalBackgroundResource(R.color.white)
                    mListView2.adapter = mListView2Adapter
                } else {
                    mListView2Adapter!!.setData(list2)
                    mListView2Adapter!!.notifyDataSetChanged()
                }
                //                    mRootView.invalidate();
            }
        }

        //二级
        mListView2.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            mListView2Adapter?.let {
                mListView2Adapter!!.setSelectedPos(position)
                mListView2Adapter!!.setSelectedBackgroundResource(R.drawable.select_gray) //选中时
            }

//            if (mListView2Adapter != null) {
//                mListView2Adapter!!.setSelectedPos(position)
//                mListView2Adapter!!.setSelectedBackgroundResource(R.drawable.select_gray) //选中时
//            }

            if (views.contains(view3)) {
                views.remove(view3)
            }
            val dictUnit = parent.getItemAtPosition(position) as MenuData
            val list3 = menuDataManager!!.getTripleColumnData(mContext, dictUnit.id)
            if (mListView3Adapter == null) {
                mListView3Adapter = MenuDialogAdapter(mContext, list3)
                mListView3Adapter!!.setHasDivider(false)
                mListView3Adapter!!.setNormalBackgroundResource(R.color.menudialog_bg_gray) //未选中
                mListView3.adapter = mListView3Adapter
            } else {
                mListView3Adapter!!.setData(list3)
                mListView3Adapter!!.notifyDataSetChanged()
            }
            views.add(view3)
            viewpager.adapter!!.notifyDataSetChanged()
            viewpager.postDelayed({ viewpager.currentItem = views.size - 1 }, 300)
        }
        mListView3.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val menuData = parent.getItemAtPosition(position) as MenuData
            setResultDate(menuData)
        }
    }

    //传递值
    private fun setResultDate(menuData: MenuData) {
        val intent = Intent()
        intent.putExtra("menu", menuData as Serializable)
        setResult(0, intent)
        finish()
    }
}