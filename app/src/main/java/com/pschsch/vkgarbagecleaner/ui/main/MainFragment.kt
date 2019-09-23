package com.pschsch.vkgarbagecleaner.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.base.BaseFragment
import com.pschsch.vkgarbagecleaner.ui.main.model.MainModel
import com.pschsch.vkgarbagecleaner.utils.requireNonNull
import com.pschsch.vkgarbagecleaner.utils.setVisible
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireNonNull(activity)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        baseViewModel = mainViewModel
        mainViewModel.progressLiveData.observe(this, Observer {
            main_progress_bar.setVisible(it.progressVisibility)
            main_data_list.setVisible(!it.progressVisibility)
            if (it.progressVisibility) {
                main_internet_error_card_view.setVisible(false)
            }
        })
        mainViewModel.baseLiveData.observe(this, Observer {
            observeBaseData(it)
        })
        mainViewModel.init()
        main_start_garbage_clean.setOnClickListener {
            AlertDialog.Builder(act)
                .setTitle(R.string.are_you_sure_to_continue_garbage_clean)
                .setPositiveButton(getString(R.string.yes_of_course)) { dialog, _ ->
                    dialog.dismiss()
                    GarbageCleanProgressDialog.newInstance(
                        mainViewModel.rawData.size,
                        mainViewModel.garbageCleanData
                    ) {
                        Toast.makeText(act, "GARBAGE CLEAN END", Toast.LENGTH_LONG).show()
                    }.show(act.supportFragmentManager, "TAG")
                    mainViewModel.startGarbageClean()
                }.setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        }
    }

    private fun observeBaseData(model: MainModel) {
        val act = requireNonNull(activity)
        enableButton(!model.connectionError && model.videos.isNotEmpty())
        if (!model.connectionError) {
            main_you_dont_have_garbage.setVisible(model.videos.isEmpty())
            main_internet_error_card_view.setVisible(false)
            main_data_list.layoutManager = LinearLayoutManager(act)
            main_data_list.adapter = MainAdapter(model.videos)
        } else {
            main_internet_error_card_view.setVisible(true)
            main_data_load_repeat.setOnClickListener {
                mainViewModel.init()
            }
        }
    }

    private fun enableButton(enable: Boolean) {
        val act = requireNonNull(activity)
        main_start_garbage_clean.isClickable = enable
        if (enable) {
            main_start_garbage_clean.backgroundTintList =
                ContextCompat.getColorStateList(act, R.color.colorAccent)
        } else {
            main_start_garbage_clean.backgroundTintList =
                ContextCompat.getColorStateList(act, R.color.colorAccentDisabled)
        }
    }
}