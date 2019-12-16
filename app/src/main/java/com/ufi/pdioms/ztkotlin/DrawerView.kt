package com.ufi.pdioms.ztkotlin



import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.view_drawer.view.*

/*import com.ufi.pdioms.cabinet.widget.AutoHideBottomUIDialog
import com.ufi.pdioms.task.R
import com.ufi.pdioms.task.activity.CompleteWidgetActivity
import com.ufi.pdioms.task.activity.SlideTextActivity
import com.ufi.pdioms.task.event.ModelNameEvent
import com.ufi.pdioms.task.module.index.LoginActivity
import com.ufi.pdioms.task.module.index.MainActivity
import com.ufi.pdioms.task.module.setting.ClearCacheFragment
import com.ufi.pdioms.task.module.setting.OfflineMapFragment
import com.ufi.pdioms.task.module.setting.VersionUpdateFragment
import com.ufi.pdioms.task.module.task.TaskMgrFragment
import com.ufi.pdioms.task.util.DisplayUtil
import kotlinx.android.synthetic.main.view_drawer.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode*/

class DrawerView : LinearLayout, View.OnClickListener {

    /*    private var logoutDialog: AutoHideBottomUIDialog? = null

        private var offlineMapFragment: OfflineMapFragment? = null
        private var clearCacheFragment: ClearCacheFragment? = null
        private var versionUpdateFragment: VersionUpdateFragment? = null*/
    private var mContext: Context? = null
    private var taskMgrFragment: TaskMgrFragment? = null
    private var isShow = false  //false 表示隐藏

    companion object {
        const val TASK_TAG = "taskMgrFragment"
        //   const val OFFLINE_MAP_TAG = "offlineMapFragment"
        //    const val CLEAR_CACHE_TAG = "clearCacheFragment"
        //   const val VERSION_UPDATE_TAG = "versionUpdateFragment"
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.view_drawer, this)
        mContext = context
        initLogoutDialog()
        initView()
        initEvent()
    }

    private fun initView() {
        /*   if (!EventBus.getDefault().isRegistered(this)) {
               EventBus.getDefault().register(this)
           }*/
    }

    fun initFragment(savedInstanceState: Bundle?) {
        var fragmentManager = (mContext as MainActivity).supportFragmentManager
        var transaction = fragmentManager.beginTransaction()
        var currentPosition = 0
        if (null != savedInstanceState) {
            taskMgrFragment = fragmentManager.findFragmentByTag(TASK_TAG) as TaskMgrFragment
            //    offlineMapFragment = fragmentManager.findFragmentByTag(OFFLINE_MAP_TAG) as OfflineMapFragment
            //   clearCacheFragment = fragmentManager.findFragmentByTag(CLEAR_CACHE_TAG) as ClearCacheFragment
            //   versionUpdateFragment = fragmentManager.findFragmentByTag(VERSION_UPDATE_TAG) as VersionUpdateFragment
        }else {
            taskMgrFragment = TaskMgrFragment()
            //   offlineMapFragment = OfflineMapFragment()
            //  clearCacheFragment = ClearCacheFragment()
            //   versionUpdateFragment = VersionUpdateFragment()
            transaction.add(R.id.testfragment_container, taskMgrFragment!!, TASK_TAG)
            //   transaction.add(R.id.fragment_container, offlineMapFragment!!, OFFLINE_MAP_TAG)
            //    transaction.add(R.id.fragment_container, clearCacheFragment!!, CLEAR_CACHE_TAG)
            //   transaction.add(R.id.fragment_container, versionUpdateFragment!!, VERSION_UPDATE_TAG)
        }
        transaction.commit()
        switchFragment(currentPosition)
    }

    private fun initEvent() {

        testtvUserName.setOnClickListener(this)
        /*  tvTaskMgr.setOnClickListener(this)
          tvOfflineMap.setOnClickListener(this)
          tvClearCache.setOnClickListener(this)
          tvVersionUpdate.setOnClickListener(this)
          tvLogout.setOnClickListener(this)
          ivClose.setOnClickListener(this)*/
    }

    override fun onClick(v: View) {
        when (v.id) {
            // fragment_container
            R.id.testtvUserName -> {
                isShow = !isShow
                if (isShow){
                    testfragment_container.visibility = View.VISIBLE
                }else{
                    testfragment_container.visibility = View.GONE
                }

                Log.e("TAG","click text tvTaskMgr: "+isShow)

            }
        }
        /*   when (v.id) {
               R.id.ivClose -> (parent as androidx.drawerlayout.widget.DrawerLayout).closeDrawers()
               R.id.tvTaskMgr -> switchFragment(0)
               R.id.tvOfflineMap -> switchFragment(1)
               R.id.tvClearCache -> switchFragment(2)
               R.id.tvVersionUpdate -> switchFragment(3)
               R.id.tvLogout -> {
                   if (!logoutDialog!!.isShowing) {
                       DisplayUtil.showDialogAsSpecificDesign(mContext, logoutDialog, 390, 190)
                   }
               }
           }*/
    }

    private fun switchFragment(position: Int) {
        var transaction = (mContext as MainActivity).supportFragmentManager.beginTransaction()
        transaction.hide(taskMgrFragment!!)
        //   transaction.hide(offlineMapFragment!!)
        //   transaction.hide(clearCacheFragment!!)
        //   transaction.hide(versionUpdateFragment!!)
        when(position) {
            0 -> transaction.show(taskMgrFragment!!)
            //  1 -> transaction.show(offlineMapFragment!!)
            //  2 -> transaction.show(clearCacheFragment!!)
            //   3 -> transaction.show(versionUpdateFragment!!)
        }
        transaction.commitAllowingStateLoss()
    }

/*    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onModelNameEvent(event: ModelNameEvent) {
        tvModelName.text = event.name
    }*/

    private fun initLogoutDialog() {
/*        logoutDialog = AutoHideBottomUIDialog(mContext!!)
        logoutDialog!!.setContentView(R.layout.dialog_logout)
        logoutDialog!!.findViewById<TextView>(R.id.tvCancel).setOnClickListener {
            logoutDialog!!.dismiss()
        }
        logoutDialog!!.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
            logoutDialog!!.dismiss()
            var intent = Intent(mContext, LoginActivity::class.java)
            mContext!!.startActivity(intent)
        }*/
    }

    fun onDestroy() {
        /*     if (EventBus.getDefault().isRegistered(this)) {
                 EventBus.getDefault().unregister(this)
             }*/
    }
}