package com.example.webunihw_eatingplaces.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.webunihw_eatingplaces.databinding.FragmentPlacelistBinding
import com.example.webunihw_eatingplaces.ui.places.adapter.PlacesAdapter

class FragmentPlaceList : Fragment() {


    private lateinit var placesViewModel: PlaceListViewModel
    private lateinit var placesAdapter: PlacesAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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