package com.raju.android.demo.fragment.headless

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raju.android.demo.databinding.ActivityHeadlessBinding

internal class HeadlessActivity : AppCompatActivity() {

    private var mHeadlessCounterFragment: HeadlessCounterFragment? = null
    private var binding: ActivityHeadlessBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadlessBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        mHeadlessCounterFragment = supportFragmentManager.findFragmentByTag("counter_fragment") as HeadlessCounterFragment?
        if (mHeadlessCounterFragment == null) {
            mHeadlessCounterFragment = HeadlessCounterFragment()
            supportFragmentManager.beginTransaction().add(mHeadlessCounterFragment!!, "counter_fragment").commit()
        }
        if (savedInstanceState == null) {
            // Setting the TextView for the count only initially
            mHeadlessCounterFragment!!.setCounterTextView(binding!!.textView)
        }
        binding?.btnStart?.setOnClickListener {
            val counterState = supportFragmentManager.findFragmentByTag("counter_fragment") as HeadlessCounterFragment
            counterState?.startCounting()
        }
        binding?.btnStop?.setOnClickListener {
            val counterState = supportFragmentManager.findFragmentByTag("counter_fragment") as HeadlessCounterFragment
            counterState?.stopCounting()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHeadlessCounterFragment?.let { headlessFragment ->
            headlessFragment.setCounterTextView(null)
        }
        mHeadlessCounterFragment = null
    }
}