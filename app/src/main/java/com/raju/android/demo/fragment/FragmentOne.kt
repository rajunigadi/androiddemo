package com.raju.android.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raju.android.demo.databinding.FragmentOneBinding
import com.raju.android.demo.utilities.afterTextChanged

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOne : Fragment() {

    private var _binding: FragmentOneBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    lateinit var nameChangeListener: NameChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        binding.etName.afterTextChanged { nameChangeListener?.onNameChanged(it) }
        return binding.root
    }

    fun setOnNameChangedListener(callback: NameChangeListener) {
        this.nameChangeListener = callback
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
        fun newInstance(param: String) =
            FragmentOne().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

interface NameChangeListener {
    fun onNameChanged(name: String)
}