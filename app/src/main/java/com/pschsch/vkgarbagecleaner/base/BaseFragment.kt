package com.pschsch.vkgarbagecleaner.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.pschsch.vkgarbagecleaner.utils.requireNonNull

open class BaseFragment : Fragment() {

    protected var baseViewModel: BaseViewModel? = null

    protected var extras : FragmentNavigator.Extras? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseViewModel?.errorMessageLiveData?.observe(this, Observer {
            showErrorMessage(it)
        })
        baseViewModel?.navigationLiveData?.observe(this, Observer {
            when (it) {
                is NavigationCommand.To -> {
                    if(extras != null){
                        findNavController().navigate(it.destinationId,null, null, extras)
                    } else findNavController().navigate(it.destinationId)
                }
            }
        })
    }

    protected fun showErrorMessage(message : String) {
        val act = requireNonNull(activity)
        Toast.makeText(act, message, Toast.LENGTH_LONG).show()
    }
}