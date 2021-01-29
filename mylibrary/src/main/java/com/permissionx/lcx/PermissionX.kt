package com.permissionx.lcx

import androidx.fragment.app.FragmentActivity

/**
 *@author lcx
 *@date 2021/1/29
 *@desc PermissionX
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback: permissionCallback) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction()
                    .add(invisibleFragment, TAG)
                    .commitNow()
            invisibleFragment
        }
        // permissions 实际上是一个数组，
        // 不可以直接将它传递给另外一个接受可变长度参数的方法中，
        // 所以需要使用* 将permissions转换成可变长度参数再传递进去
        // *表示将一个数组转为可变长度参数
        fragment.requestNow(callback, *permissions)
    }
}