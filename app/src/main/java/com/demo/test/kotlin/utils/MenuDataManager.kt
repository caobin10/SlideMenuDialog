package com.demo.test.kotlin.utils

import android.content.Context
import com.demo.test.kotlin.bean.MenuData
import com.demo.test.kotlin.utils.MenuUtil.getPositions

/**
 * Created by LaiYingtang on 2016/5/22.
 * 菜单数据管理类
 */
class MenuDataManager  //构造器
{
    //使用枚举找
    enum class MenuType(  //菜单数据放在txt
        var fileName: String
    ) {
        POSITION_FUNCTION("position_function.txt");
    }

    //获取列数据
    fun getTripleColumnData(mContext: Context?, flag: Int): List<MenuData>? {
        //把所有的menu的filName给MenuUtil解析
        return getPositions(mContext!!, flag, MenuType.POSITION_FUNCTION.fileName)
    }

    companion object {
        //单例
        private var mInstance: MenuDataManager? = null
        val instance: MenuDataManager?
            get() {

                mInstance = mInstance ?: MenuDataManager()

//                if (mInstance == null) {
//                    mInstance = MenuDataManager()
//                }
                return mInstance
            }
    }
}