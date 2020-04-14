package com.covid19.app.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covid19.app.R
import com.covid19.app.ui.home.list.viewholder.StateWiseDetailVH
import java.util.*

class StateWiseDetailAdapter :
    RecyclerView.Adapter<StateWiseDetailVH>() {
    var data: List<ListData> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): StateWiseDetailVH {
        return when (itemType) {
            ListDataType.STATE_HEADER -> StateWiseDetailVH.StateVH(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.vh_state_detail_header, viewGroup, false)
            )
            else -> StateWiseDetailVH.DistrictVH(
                LayoutInflater.from(
                    viewGroup.context
                ).inflate(R.layout.vh_district_detail_header, viewGroup, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: StateWiseDetailVH, position: Int) {
        val listData = data[position]
        when (viewHolder) {
            is StateWiseDetailVH.StateVH -> {
                viewHolder.state.text = listData.name
                viewHolder.confirmed.text = listData.confirmed.toString()
            }
            is StateWiseDetailVH.DistrictVH -> {
                viewHolder.district.text = listData.name
                viewHolder.confirmed.text = listData.confirmed.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    fun setListData(listData: List<ListData>) {
        this.data = listData
        notifyDataSetChanged()
    }
}