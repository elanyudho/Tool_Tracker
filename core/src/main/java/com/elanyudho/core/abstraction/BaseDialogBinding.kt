package com.elanyudho.core.abstraction

import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.elanyudho.core.R
import com.elanyudho.core.util.extension.dpToPx

abstract class BaseDialogBinding<T: ViewBinding>(
    val isFullScreen: Boolean, val maxSize: Int
) : DialogFragment() {

    private var _binding: T? = null

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    protected val binding: T
        get() {
            if (_binding == null) {
                throw IllegalArgumentException("View binding is not initialized yet")
            }
            return _binding!!
        }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        dialog?.window?.apply {
            if (!isFullScreen) {
                setLayout(getBetterSize(maxSize), ViewGroup.LayoutParams.WRAP_CONTENT)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFullScreen) {
            setStyle(STYLE_NORMAL, R.style.Theme_Tool_FullScreenDialog)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    protected abstract fun setupView()

    @TargetApi(Build.VERSION_CODES.R)
    private fun getBetterSize(max: Int = 750): Int {
        val displayMetrics = DisplayMetrics()
        requireContext().display?.getRealMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val whitespaceSize = screenWidth / 8
        val maxViewWidth = screenWidth - whitespaceSize
        val optimalViewWidth = maxViewWidth.coerceAtMost(max.dpToPx(resources)) // Limit the maximum
        return optimalViewWidth
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}