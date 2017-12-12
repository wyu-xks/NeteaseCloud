package com.wyuxks.neteasecloud.ui.base.baseadpter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.AdapterView

import java.util.ArrayList

/**
 * Created by jingbin on 2016/11/25
 */
abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseRecyclerViewHolder<T>>() {

    var data: ArrayList<T> = ArrayList<T>()
    open var listener: OnItemClickListener<T>? = null


    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T>, position: Int) {
        holder.onBaseBindViewHolder(data.get(position), position)
        if (listener != null) {
            holder.setOnItemClickListener(listener)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getData(): List<T> = data

    fun addAll(data: List<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun add(`object`: T) {
        data.add(`object`)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun remove(`object`: T) {
        data.remove(`object`)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeAll(data: List<T>) {
        this.data.retainAll(data)
        notifyDataSetChanged()
    }

//    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener<T>) {
//        this.listener = listener
//    }


}
