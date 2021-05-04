package com.raju.android.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.raju.android.demo.fragment.FragmentOne
import com.raju.android.demo.fragment.FragmentTwo
import com.raju.android.demo.fragment.NameChangeListener

class MyFragmentActivity : AppCompatActivity(), NameChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if(fragment is FragmentOne) {
            fragment.setOnNameChangedListener(this)
        }
    }

    override fun onNameChanged(name: String) {
        val callingFragment = supportFragmentManager.findFragmentById(R.id.fragmentTwo) as FragmentTwo
        callingFragment.updateName(name)
    }
}