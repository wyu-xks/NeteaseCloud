package com.wyuxks.neteasecloud.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import java.util.ArrayList

/**
 *  Author : xks
 *  Data : 2017/11/20 0020
 *  Des :
 */
object PermissionUtil{

    fun hasPermission(context: Context, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        for (permission in permissions) {
            val hasPermission = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            if (!hasPermission) {
                return false
            }
        }
        return true
    }

    fun requestPermissions(o: Any, requestCode: Int, vararg permissions: String) {
        if (o is Activity) {
            ActivityCompat.requestPermissions(o, permissions, requestCode)
        } else (o as? android.support.v4.app.Fragment)?.requestPermissions(permissions, requestCode)
    }



    fun showDialog(context: Activity, cancelOnclick: DialogInterface.OnClickListener, Message: String) {
        val builder = AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("权限申请")
                .setMessage(Message)
                .setNegativeButton("取消", cancelOnclick)
                .setPositiveButton("去设置") { dialog, which ->
                    val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
                    context.startActivity(intent)
                    context.finish()
                }
        val dialog = builder.create()
        dialog.show()
        //        if (dialog.getButton(DialogInterface.BUTTON_POSITIVE) != null && dialog.getButton(DialogInterface.BUTTON_NEGATIVE) != null) {
        //            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.footer_menu_sel));
        //            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.footer_menu_unsel));
        //        }
    }

    fun hasAlwaysDeniedPermission(activity: Activity, deniedPermissions: List<String>): Boolean {
        for (deniedPermission in deniedPermissions) {
            if (shouldShowRationalePermissions(activity, deniedPermission)) {
                return true
            }
        }
        return false
    }

    fun shouldShowRationalePermissions(o: Any, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false
        }

        var rationale = false
        for (permission in permissions) {
            if (o is Activity) {
                rationale = ActivityCompat.shouldShowRequestPermissionRationale(o, permission)
            } else if (o is android.support.v4.app.Fragment) {
                rationale = o.shouldShowRequestPermissionRationale(permission)
            } else if (o is android.app.Fragment) {
                rationale = o.shouldShowRequestPermissionRationale(permission)
            }
            if (rationale) {
                return true
            }
        }
        return false
    }
}