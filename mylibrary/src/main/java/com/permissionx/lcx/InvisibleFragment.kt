package com.permissionx.lcx

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.security.Permissions

/**
 * typealias 关键字用于给任意类型指定一个别名
 * 在调用的地方使用该别名
 */
typealias permissionCallback = (Boolean, List<String>) -> Unit

/**
 *@author lcx
 *@date 2021/1/29
 *@desc InvisibleFragment
 */
class InvisibleFragment: Fragment() {

    private var callback: permissionCallback? = null

    fun requestNow(cb: permissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}