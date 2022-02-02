package com.example.webunihw_eatingplaces.ui.places

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webunihw_eatingplaces.databinding.FragmentPlacelistBinding
import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.example.webunihw_eatingplaces.ui.places.adapter.PlacesAdapter
import kotlinx.android.synthetic.main.fragment_placelist.*


class FragmentPlaceList : Fragment() {
    val placelistViewModel by viewModels<PlaceListViewModel>()
    //private var placelistViewModel: PlaceListViewModel by viewModels()
    private lateinit var placesAdapter: PlacesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: FragmentPlacelistBinding? = null
    private val binding
        get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlacelistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }//ONCREATEview


    override fun onResume() {
        super.onResume()
        placelistViewModel.getPlaces()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("tartalom", "első")

        placelistViewModel.getPlacesLiveData()
            .observe(viewLifecycleOwner, { placeListResult -> render(placeListResult) })

        Log.e("tartalom", "második")

    }



    private fun render(result: PlaceListViewState) {
        Log.e("tartalom", result.toString())
        when (result) {
            is DownloadPlacesInProgress -> {
                binding.progressbarPlacelist.visibility = View.VISIBLE
            }
            is PlaceListResponseSuccess -> {
                binding.progressbarPlacelist.visibility = View.GONE
                processResponse(result.data)
                Log.e("tartalom", result.toString())
            }
            is PlaceListResponseError -> {
                binding.progressbarPlacelist.visibility = View.GONE
                Toast.makeText(
                    //TODO mi az a requireContext?
                    requireContext(),
                    "HIBA"+result.exceptionMsg,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    private fun processResponse(placelistData: PlaceListResult?) {
        if (placelistData != null) {
            Log.e("eredmeny", placelistData.places.toString())
            //recyclerview_places.adapter = placelistData.places?.let { PlacesAdapter(it) }
            recyclerview_places.adapter = context?.let { PlacesAdapter(it, placelistData.places!!) }

            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                .apply { recyclerview_places.layoutManager = this }


            linearLayoutManager = LinearLayoutManager(requireContext()).apply {
                recyclerview_places.layoutManager = this

            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentPlaceList()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}