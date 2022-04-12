package com.demo.test.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatDrawableManager.get
import com.demo.test.kotlin.R
import com.demo.test.kotlin.bean.MenuData

/**
 * 要显示的adapter
 * Created by LaiYingtang on 2016/5/24.
 */
class MenuDialogAdapter( private val mContext: Context?,private var menuDatas: List<MenuData>?) : BaseAdapter() {
    private var selectedPos = -1
    private var mSelectedBackgroundResource = 0//选中item时的背景颜色
    private var mNormalBackgroundResource = 0//为选中的背景颜色
    private var hasDivider = true
    fun setSelectedBackgroundResource(mSelectedBackgroundResource: Int) {
        this.mSelectedBackgroundResource = mSelectedBackgroundResource
    }

    fun setNormalBackgroundResource(mNormalBackgroundResource: Int) {
        this.mNormalBackgroundResource = mNormalBackgroundResource
    }

    fun setHasDivider(hasDivider: Boolean) {
        this.hasDivider = hasDivider
    }

    //选中的position,及时更新数据
    fun setSelectedPos(selectedPos: Int) {
        this.selectedPos = selectedPos
        notifyDataSetChanged()
    }

    fun setData(data: List<MenuData>?) {
        menuDatas = data
        notifyDataSetChanged()
    }

//    override fun getCount(): Int {
//        return if (menuDatas == null) {
//            0
//        } else menuDatas!!.size
//    }
    override fun getCount() = if (menuDatas == null) 0 else menuDatas!!.size

//    override fun getItem(position: Int): Object {
//        return if (menuDatas == null) null else menuDatas[position]
//    }

    override fun getItem(position: Int) = if (menuDatas == null) null else menuDatas!![position]

//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
    override fun getItemId(position: Int) = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_menu_item, null)
        }
        val itemLayout = ViewHolder.get<LinearLayout>(convertView!!, R.id.menu_item_ly)
        val nameText = ViewHolder.get<TextView>(convertView!!, R.id.menu_item_textview)
        val dividerTextView = ViewHolder.get<TextView>(convertView!!, R.id.menu_item_divider)
        val menuData = menuDatas!![position]
        nameText!!.text = menuData.name //设置标题
        convertView!!.isSelected = selectedPos == position //设置选中时的view
        nameText!!.isSelected = selectedPos == position

        //选中后的标题字体颜色
        nameText.setTextColor(if (selectedPos == position) -0xff4b37 else -0xcccccd)
        //选中与未选中的背景色
        if (mNormalBackgroundResource == 0) mNormalBackgroundResource = R.color.white
        if (mSelectedBackgroundResource != 0) itemLayout!!.setBackgroundResource(if (selectedPos == position) mSelectedBackgroundResource else mNormalBackgroundResource)

        //隐藏view
        dividerTextView!!.visibility = if (hasDivider) View.VISIBLE else View.INVISIBLE
        return convertView
    }
}