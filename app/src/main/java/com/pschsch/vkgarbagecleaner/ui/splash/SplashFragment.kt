package com.pschsch.vkgarbagecleaner.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.base.BaseFragment
import com.pschsch.vkgarbagecleaner.utils.requireNonNull
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        baseViewModel = splashViewModel
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val p = splash_vk_logo to getString(R.string.transition_name_logo)
        val p1 = splash_background to getString(R.string.transition_name_background)
        extras = FragmentNavigatorExtras(p, p1)
        splashViewModel.init()
    }
}