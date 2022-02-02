package com.example.webunihw_eatingplaces.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webunihw_eatingplaces.databinding.FragmentMapBinding


class FragmentMap : Fragment() {
private var _binding : FragmentMapBinding? = null
private val binding
    get() = _binding!!

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
    return binding.root



}//ONCREATEVIEW

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


}

companion object {

    @JvmStatic
    fun newInstance() =
        FragmentMap()
}


override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}