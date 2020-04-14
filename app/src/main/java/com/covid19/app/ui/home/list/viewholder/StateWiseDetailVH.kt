package com.covid19.app.ui.home.list.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vh_district_detail_header.view.*
import kotlinx.android.synthetic.main.vh_state_detail_header.view.*

sealed class StateWiseDetailVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class StateVH(view: View) : StateWiseDetailVH(view) {
        val state: TextView = view.state_name
        val confirmed: TextView = view.vh_state_detail_header_confirmed
    }

    class DistrictVH(view: View) : StateWiseDetailVH(view) {
        val district: TextView = view.district_name
        val confirmed: TextView = view.district_confirmed
    }
}