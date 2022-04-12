package com.demo.test.kotlin.adapter

import android.util.SparseArray
import android.view.View

/**
 * 用于adapter显示的viewHolder
 * Created by LaiYingtang on 2016/5/24.
 */
object ViewHolder {
     fun <T : View?> get(view: View, id: Int): T? {
        //j节省内存，提高性能，使用SparseArray
        var viewHolder = view.tag as? SparseArray<View?>

        if (viewHolder == null) {
            viewHolder = SparseArray()

            view.tag = viewHolder
        }
        var childView = viewHolder[id]
        if (childView == null) {
            childView = view.findViewById(id)
            viewHolder.put(id, childView)
        }
        return childView as T?
    }
}