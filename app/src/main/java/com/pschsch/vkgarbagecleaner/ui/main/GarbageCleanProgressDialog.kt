package com.pschsch.vkgarbagecleaner.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pschsch.vkgarbagecleaner.R
import kotlinx.android.synthetic.main.fragment_garbage_clean_progress_dialog.*

class GarbageCleanProgressDialog : DialogFragment() {

    private var data: LiveData<Int>? = null
    private var maxValue : Int = 0
    private var dismissListener : (()->Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_garbage_clean_progress_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        garbage_clean_progress.maxProgress= maxValue.toDouble()
        data?.observe(this, Observer {
            garbage_clean_progress.setCurrentProgress(it.toDouble())
            garbage_clean_progress.setProgressTextAdapter {value ->
                "$value of $maxValue"
            }
            if(it == maxValue){
                dismissListener?.invoke()
                this.dismiss()
            }
        })
    }

    companion object {
        fun newInstance(maxValue : Int, progressData: LiveData<Int>, dismissListener : ()->Unit): GarbageCleanProgressDialog {
            return GarbageCleanProgressDialog().apply {
                this.maxValue = maxValue
                this.data = progressData
                this.dismissListener = dismissListener
            }
        }
    }
}