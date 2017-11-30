package com.wyuxks.neteasecloud.utils

import android.util.SparseArray
import android.view.View

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
fun <T : View> View.findViewOften(viewId: Int): T {
    var viewHolder: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
    tag = viewHolder
    var childView: View? = viewHolder.get(viewId)
    if (null == childView) {
        childView = findViewById(viewId)
        viewHolder.put(viewId, childView)
    }
    return childView as T
}