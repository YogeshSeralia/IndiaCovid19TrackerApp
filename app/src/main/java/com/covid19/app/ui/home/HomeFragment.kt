package com.covid19.app.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.covid19.app.R
import com.covid19.app.data.shared.Result
import com.covid19.app.ui.home.list.ListData
import com.covid19.app.ui.home.list.StateWiseDetailAdapter
import com.covid19.app.ui.home.map.AnchorSheetBehavior
import com.covid19.app.ui.home.map.AnchorSheetBehavior.*
import com.covid19.app.ui.home.map.MapClusterItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mLocation: LatLng
    private lateinit var bsBehavior: AnchorSheetBehavior<NestedScrollView>
    private lateinit var mUIContext: Context
    private lateinit var googleMap: GoogleMap
    private val stateWiseDetailAdapter = StateWiseDetailAdapter()
    private lateinit var homeViewModel:HomeViewModel
    private val mClusterManager: ClusterManager<MapClusterItem> by lazy {
        ClusterManager<MapClusterItem>(mUIContext, googleMap)
    }

    private val supportMapFragment: SupportMapFragment
        get() {
            val mapFragment: SupportMapFragment =
                childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
            return mapFragment
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        MainScope().launch {
            homeViewModel.stateWiseData.collect {
                when(it) {
                    is Result.Success -> stateWiseDetailAdapter.setListData(it.data as List<ListData>)
                    is Result.Loading -> progress_bar.visibility = if(it.isLoading) VISIBLE else GONE
                    is Result.Error -> Toast.makeText(activity,it.throwable.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mUIContext = this.activity!!

        val india = LatLng(20.5937, 78.9629)
        mLocation = india

        supportMapFragment.getMapAsync(this)
        setupAnchorSheetBehavior(view)
        setupRecyclerView(view)

        return view
    }

    private fun setupRecyclerView(view: View) {
        view.recycler_view_nested.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        view.recycler_view_nested.adapter = stateWiseDetailAdapter
    }

    private fun setupAnchorSheetBehavior(view: View) {
        bsBehavior = from(view.nested_scrollview)
        bsBehavior.state = STATE_COLLAPSED
        bsBehavior.setAnchorOffset(0.2f)
        bsBehavior.setAnchorSheetCallback(object : AnchorSheetBehavior.AnchorSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val offset = bottomSheet.height * slideOffset
                when (bsBehavior.state) {
                    STATE_DRAGGING -> {
                        setMapPaddingBottom(offset)
                        //reposition marker at the center
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mLocation))
                    }
                    STATE_SETTLING -> {
                        setMapPaddingBottom(offset)
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mLocation))
                    }
                    else -> {
                    }
                }
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_COLLAPSED -> {
                    }
                    else -> {
                    }
                }
            }
        })
    }

    private fun setMapPaddingBottom(offset: Float) {
        //From 0.0 (min) - 1.0 (max) // bsExpanded - bsCollapsed;
        val maxMapPaddingBottom = 1.0f
        googleMap.setPadding(0, 0, 0, Math.round(offset * maxMapPaddingBottom))
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 4f))
        setupClusterer()
    }

    // use mClusterManager.addItem(ClusterItem); to show up the clustering
    private fun setupClusterer() {
        googleMap.setOnCameraIdleListener(mClusterManager)
        googleMap.setOnMarkerClickListener(mClusterManager)
    }
}
