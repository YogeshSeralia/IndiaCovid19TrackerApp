package com.covid19.app.ui.home.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MapClusterItem(
    val item_title: String,
    val item_snippet: String,
    val item_position: LatLng
) : ClusterItem {
    override fun getSnippet(): String {
        return item_snippet
    }

    override fun getTitle(): String {
        return item_title
    }

    override fun getPosition(): LatLng {
        return item_position
    }
}