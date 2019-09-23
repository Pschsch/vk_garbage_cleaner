package com.pschsch.vkgarbagecleaner.ui.auth

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.base.BaseFragment
import com.pschsch.vkgarbagecleaner.utils.requireNonNull
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : BaseFragment(), VkAuthConsumer {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            sharedElementEnterTransition = TransitionSet().apply {
                addTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.fade))
                addTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.move))
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        baseViewModel = authViewModel
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireNonNull(activity)
        auth_no_i_wont_button.setOnClickListener {
            act.onBackPressed()
        }
        auth_authorize_button.setOnClickListener {
            VK.login(act, listOf(VKScope.VIDEO))
        }
    }

    override fun onVkSuccess(token: VKAccessToken) {
        authViewModel.onVkSuccess(token)
    }

    override fun onVkError(error: Int) {
        authViewModel.onVkError(error)
    }
}