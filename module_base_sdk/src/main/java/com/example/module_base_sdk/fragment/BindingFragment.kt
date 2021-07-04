package com.example.module_base_sdk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding


abstract class BindingFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(), UiComponent, LiveDataView {
    private var internalBinding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = requireNotNull(internalBinding) as VB

    override val extensionsLifecycleOwner: LifecycleOwner
        get() = viewLifecycleOwner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        internalBinding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(internalBinding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        listenUiUpdates()
        bindView()
    }

    override fun onDestroyView() {
        internalBinding = null
        super.onDestroyView()
    }
}