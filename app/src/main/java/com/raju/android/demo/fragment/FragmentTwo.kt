package com.raju.android.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raju.android.demo.databinding.FragmentTwoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTwo : Fragment() {

    private var _binding: FragmentTwoBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateName(text: String) {
        binding.tvName.text = text
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FragmentOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FragmentTwo().apply {
                arguments = Bundle().apply {

                }
            }
    }
}