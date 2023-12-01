package com.newway.nwalert

import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.hardware.display.DisplayManagerCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.newway.nwalert.databinding.FragmentNWAlertBinding


interface NWAlertInterface {
    fun onClickButton(button:NWButton)
}
class NWAlertFragment : DialogFragment() {

    companion object {
        @JvmStatic
        fun newInstance(buttons:List<NWButton>,title:String,message:String? = null,options:NWOptions = NWOptions()) : NWAlertFragment {
            val dialog = NWAlertFragment()
            dialog.options = options
            dialog.buttons = buttons
            dialog.title = title
            dialog.message = message
            return dialog
        }
    }

    private lateinit var binding: FragmentNWAlertBinding
    var listener : NWAlertInterface? = null

    var options = NWOptions()
    var buttons: List<NWButton> = listOf()
    var title : String = ""
    var message : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Theme_NWAlert_Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNWAlertBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    override fun isCancelable(): Boolean {
        return options.isCancelable
    }
    fun show(fragmentManager: FragmentManager) {
        if (allowShow(fragmentManager)){
            this.show(fragmentManager, "NWAlertFragment")
        }
    }
    private fun allowShow(fragmentManager: FragmentManager) : Boolean {
        return (!isAdded && !fragmentManager.isDestroyed && !fragmentManager.isStateSaved && dialog == null && !isVisible)
    }
    fun hide(){
        if (isAdded){
            dismissAllowingStateLoss()
        }
    }

    // FUN

    private fun setUpView(){
        binding.lbTitle.text = title
        binding.lbTitle.setTextColor(options.titleColor)
        binding.lbTitle.typeface = options.fontTitle
        binding.lbTitle.textSize = options.titleTextSize

        binding.lbMessage.text = message
        binding.lbMessage.setTextColor(options.messageColor)
        binding.lbMessage.typeface = options.fontMessage
        binding.lbMessage.textSize = options.messageTextSize

        binding.cardView.setCardBackgroundColor(options.backgroundColor)
        binding.cardView.radius = convertDpToPixel(options.cornerContainer)

        binding.linearContent.post {
            setWidthForContainer()
            context?.let { ctx ->
                buttons.forEach { button ->
                    val btn = NWButtonView(ctx)
                    btn.setUpView(button = button,options = options)
                    btn.setOnClickListener {
                        hide()
                        listener?.onClickButton(button)
                    }
                    binding.linearContent.addView(btn)
                }
            }
        }
    }

    private fun convertDpToPixel(dp: Float): Float {
        return if (context != null) {
            val resources = requireContext().resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
    private fun Activity.screenSize() : DisplayMetrics {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            val defaultDisplay =
                DisplayManagerCompat.getInstance(this).getDisplay(Display.DEFAULT_DISPLAY)
            val displayContext = createDisplayContext(defaultDisplay!!)

            displayContext.resources.displayMetrics

        } else {

            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics
        }
    }

    private fun setWidthForContainer(){
        activity?.let { act ->
            val widthScreen = act.screenSize().widthPixels
            val marginPixel = convertDpToPixel(options.marginContainer)
            val maxWidth = convertDpToPixel(options.maxWidthContainer)
            var width = widthScreen - (marginPixel * 2)
            if (width < maxWidth){
                width = maxWidth
            }
            val params =  FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.width = width.toInt()
            binding.linearContent.layoutParams = params
        }
    }
}