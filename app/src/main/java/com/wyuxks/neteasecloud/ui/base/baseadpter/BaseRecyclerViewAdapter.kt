package com.wyuxks.neteasecloud.ui.base.baseadpter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.AdapterView

import java.util.ArrayList

/**
 * Created by jingbin on 2016/11/25
 */
abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseRecyclerViewHolder<T>>() {

    var data: MutableList<T> = ArrayList<T>()
//    protected lateinit var listener: AdapterView.OnItemClickListener<T>

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T>, position: Int) {
            holder.onBaseBindViewHolder(data.get(position), position)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun addAll(data: List<T>) {
        this.data.addAll(data)
    }

    fun add(`object`: T) {
        data.add(`object`)
    }

    fun clear() {
        data.clear()
    }

    fun remove(`object`: T) {
        data.remove(`object`)
    }

    fun remove(position: Int) {
        data.removeAt(position)
    }

    fun removeAll(data: List<T>) {
        this.data.retainAll(data)
    }

//    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener<T>) {
//        this.listener = listener
//    }



}
