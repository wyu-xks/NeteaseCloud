package com.wyuxks.neteasecloud.ui.base.baseadpter

/**
 * Created by jingbin on 2016/3/2.
 */
interface OnItemClickListener<T> {
    fun onClick(t: T, position: Int)
}
