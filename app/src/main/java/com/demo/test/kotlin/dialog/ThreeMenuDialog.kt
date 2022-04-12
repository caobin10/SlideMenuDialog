package com.demo.test.kotlin.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import com.demo.test.kotlin.R
import com.demo.test.kotlin.adapter.MenuDialogAdapter
import com.demo.test.kotlin.adapter.MyPagerAdapter
import com.demo.test.kotlin.bean.MenuData
import com.demo.test.kotlin.utils.MenuDataManager
import java.util.*
import kotlinx.android.synthetic.main.three_menu_dialog.*

/**
 * 三级菜单列表
 * Created by LaiYingtang on 2016/5/25.
 */
class ThreeMenuDialog(context: Context?) : SecondMenuDialog(context!!) {
    //private var mViewPager : MyViewPager? = null //滑动viewPager
//    private var mRootView : LinearLayout? = null //需要显示的layout
    //三个菜单级view
//    private var view1: View? = null
//    private var view2: View? = null
//    private var view3: View? = null

    //每个菜单列表都是一个listView
//    private var mListView1: ListView? = null
//    private var mListView2: ListView? = null
//    private var mListView3: ListView? = null

    //列表显示数据必须要的adapter
    private var mListView1Adapter: MenuDialogAdapter? = null
    private var mListView2Adapter: MenuDialogAdapter? = null
    private var mListView3Adapter: MenuDialogAdapter? = null
    private val views: MutableList<View?> = ArrayList() //数据集合
    var mDictDataManager = MenuDataManager.instance //全部数据

    //接口，点击监听
    private var menuItemClickListener: MenuItemClickListener? = null

    init {
//        mWidth = mContext.resources.displayMetrics.widthPixels //获取屏幕参数
        mContentView = LayoutInflater.from(context).inflate(R.layout.three_menu_dialog, null)
        //初始化控件及对控件操作
        initViews()
        setTitle("三级列表") //设置title
    }


    private fun initViews() {
//        mRootView = findViewById(R.id.rootview) as LinearLayout
//        mViewPager = findViewById(R.id.viewpager) as MyViewPager
        viewpager.offscreenPageLimit = 2 //显示2页

        //为view加载layout,由于三个级的菜单都是只有一个listView，这里就只xie一个了
        val inflater = LayoutInflater.from(mContext)
        val view1 = inflater.inflate(R.layout.pager_number, null)
        val view2 = inflater.inflate(R.layout.pager_number, null)
        val view3 = inflater.inflate(R.layout.pager_number, null)

        //获取id
        val mListView1 = view1.findViewById<View>(R.id.listview) as ListView
        val mListView2 = view2.findViewById<View>(R.id.listview) as ListView
        val mListView3 = view3.findViewById<View>(R.id.listview) as ListView

        //获取列表数据了
        val list = mDictDataManager!!.getTripleColumnData(mContext, 0)
        //关联adapter
        mListView1Adapter = MenuDialogAdapter(mContext, list)
        mListView1Adapter!!.setSelectedBackgroundResource(R.drawable.select_white) //选中时的背景
        mListView1Adapter!!.setHasDivider(false)
        mListView1Adapter!!.setNormalBackgroundResource(R.color.menudialog_bg_gray) //未选中
        mListView1.adapter = mListView1Adapter
        views.add(view1)
        views.add(view2) //当前是第三级菜单，所以前面已经存在第一，第二菜单了

        //关联
        viewpager.adapter = MyPagerAdapter(views)
        //触屏监听
        rootview.setOnTouchListener { v, event -> viewpager.dispatchTouchEvent(event) }

        //view1的listView的点击事件
        //点击事件
        mListView1.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            mListView1Adapter?.let {
                mListView1Adapter!!.setSelectedPos(position)
            }

//            if (mListView1Adapter != null)
//                mListView1Adapter!!.setSelectedPos(position)

            mListView2Adapter?.let {
                mListView2Adapter!!.setSelectedPos(-1)
            }

//            if (mListView2Adapter != null)
//                mListView2Adapter!!.setSelectedPos(-1)

            if (views.contains(view3)) {
                views.remove(view3)
                viewpager.adapter!!.notifyDataSetChanged() //立即更新adapter数据
            }
            val menuData = parent.getItemAtPosition(position) as MenuData //得到第position个menu子菜单
            if (menuData.id == 0) { //不限
                if (mListView2Adapter != null) {
                    mListView2Adapter!!.setData(ArrayList())
                    mListView2Adapter!!.notifyDataSetChanged() //刷新
                }
                setDictItemClickListener(menuData)
            } else {
                val list2 = mDictDataManager!!.getTripleColumnData(mContext, menuData.id)
                if (mListView2Adapter == null) {
                    mListView2Adapter = MenuDialogAdapter(mContext, list2)
                    mListView2Adapter!!.setNormalBackgroundResource(R.color.white)
                    mListView2.adapter = mListView2Adapter
                } else {
                    mListView2Adapter!!.setData(list2)
                    mListView2Adapter!!.notifyDataSetChanged()
                }
            }
        }

        //view2的listView点击
        mListView2.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            mListView2Adapter?.let {
                mListView2Adapter!!.setSelectedPos(position)
                mListView2Adapter!!.setSelectedBackgroundResource(R.drawable.select_gray)
            }

//            if (mListView2Adapter != null) {
//                mListView2Adapter!!.setSelectedPos(position)
//                mListView2Adapter!!.setSelectedBackgroundResource(R.drawable.select_gray)
//            }

            if (views.contains(view3)) {
                views.remove(view3)
            }

            //从第二级菜单的基础上加载第三级菜单
            val menuData = parent.getItemAtPosition(position) as MenuData
            val list3 = mDictDataManager!!.getTripleColumnData(mContext, menuData.id)
            if (mListView3Adapter == null) {
                mListView3Adapter = MenuDialogAdapter(mContext, list3)
                mListView3Adapter!!.setHasDivider(false)
                mListView3Adapter!!.setNormalBackgroundResource(R.color.menudialog_bg_gray)
                mListView3.adapter = mListView3Adapter
            } else {
                mListView3Adapter!!.setData(list3)
                mListView3Adapter!!.notifyDataSetChanged()
            }

            //放入第三级菜单列表
            views.add(view3!!)
            viewpager.adapter!!.notifyDataSetChanged()
            if (viewpager.currentItem != 1) {
                viewpager.postDelayed({
                    viewpager.currentItem = 1 //选一个
                }, 300)
            }
        }

        //最后就是第三级菜单的点击了
        mListView3.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val menuData = parent.getItemAtPosition(position) as MenuData
            setDictItemClickListener(menuData) //选中点击的子菜单，去设置titleName
        }
    }

    private fun setDictItemClickListener(menuData: MenuData) {

        menuItemClickListener?.let {
            menuItemClickListener!!.onMenuItemClick(menuData)
        }

//        if (menuItemClickListener != null) {
//            menuItemClickListener!!.onMenuItemClick(menuData)
//        }

        dismiss()
    }

    fun setOnItemClickListener(listener: MenuItemClickListener) {
        menuItemClickListener = listener
    }

    interface MenuItemClickListener {
        fun onMenuItemClick(menuData: MenuData?)
    }
}