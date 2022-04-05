package com.upclicks.ffc.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.upclicks.ffc.session.SessionHelper
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes val resId: Int?) : Fragment() {
    @Inject
    lateinit var sessionHelper: SessionHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return resId?.let {
            inflater.inflate(resId, container, false)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLayout()
        setUpClickListeners()
        setUpLiveData()
    }
    fun shoMsg(msg: String, type: String) {
        MotionToast.createColorToast(
            requireActivity(),
            "App",
            msg,
            type,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION, null
        )
    }
    /**
     * Ensures that we don't forget to set up our binding class
     */
    abstract fun setUpLayout()

    /**
     * Given the fact that a fragment may lack view click listeners, we will not enforce this
     */
    open fun setUpClickListeners() {}


    /**
     * Given the fact that a fragment may lack not observe any LiveData, we will also not enforce this
     */
    open fun setUpLiveData() {

    }

}