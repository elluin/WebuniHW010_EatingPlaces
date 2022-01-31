package com.example.webunihw_eatingplaces.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webunihw_eatingplaces.databinding.FragmentPlacelistBinding


class FragmentMap : Fragment() {
private var _binding : FragmentPlacelistBinding? = null
private val binding
    get() = _binding!!

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentPlacelistBinding.inflate(layoutInflater, container, false)
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

//        _binding?.buttonIn?.setOnClickListener{
//
//            val sumOfIn = InOut(null, _binding!!.edittextIn.text.toString().toInt(),0)
//            val dbThread = Thread {
//                AppDatabase.getInstance(requireContext()).inoutDao().insertGrades(sumOfIn)
//            }
//            dbThread.start()
//
//            val intent =  Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
//        }
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