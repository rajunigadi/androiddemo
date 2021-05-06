package com.raju.android.demo.fragment.headless

import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

class HeadlessCounterFragment : Fragment() {

    private var mSimpleCounterAsync = SimpleCounterAsync()
    private var mCounterTextView: WeakReference<TextView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun setCounterTextView(textView: TextView?) {
        mCounterTextView = WeakReference(textView)
    }

    fun startCounting() {
        if (mSimpleCounterAsync.status == AsyncTask.Status.RUNNING) return
        if (mSimpleCounterAsync.status == AsyncTask.Status.FINISHED) mSimpleCounterAsync =
            SimpleCounterAsync()
        mSimpleCounterAsync.execute()
    }

    fun stopCounting() {
        if (mSimpleCounterAsync.status != AsyncTask.Status.RUNNING) return
        mSimpleCounterAsync.cancel(true)
    }

    internal inner class SimpleCounterAsync : AsyncTask<Void?, Int, Void?>() {

        private var mViewIsGoneCount = 0

        override fun doInBackground(vararg voids: Void?): Void? {
            var count = 0
            while (!isCancelled) {
                count++
                publishProgress(count)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    return null
                }
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val view = mCounterTextView!!.get()
            if (view != null) {
                view.text = "Counter: " + values[0]
                mViewIsGoneCount = 0
            } else {
                mViewIsGoneCount++
                if (mViewIsGoneCount < 5) {
                    Toast.makeText(activity, "TextView is not there for $mViewIsGoneCount time.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "TextView was gone for too long. Terminating the counter.", Toast.LENGTH_SHORT).show()
                    cancel(true)
                }
            }
        }
    }
}