package com.upclicks.ffc.ui.checkout

import android.view.View
import android.webkit.WebSettings
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityOnlinePaymentBinding
import com.upclicks.ffc.ui.checkout.helper.ClientWebView

class OnlinePaymentActivity : BaseActivity() {

    lateinit var binding: ActivityOnlinePaymentBinding

    override fun getLayoutResourceId(): View {
        binding = ActivityOnlinePaymentBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.pluginState = WebSettings.PluginState.ON
        binding.webView.webViewClient =
            ClientWebView(
                this,
                intent.getIntExtra("sec", 1), intent.getStringExtra("callbackUrl").toString() + ""
            )
        binding.webView.loadUrl(intent.getStringExtra("url")!!)
    }

    override fun onBackPressed() {

    }
}