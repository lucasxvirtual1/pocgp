package com.lucasxvirtual.googlepaypoc.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lucasxvirtual.googlepaypoc.R
import java.io.Serializable

internal class ThreeDSecureWebActivity : AppCompatActivity() {
    companion object {
        val EXTRA_REQUEST = "EXTRA_REQUEST"
        val EXTRA_RESPONSE = "EXTRA_RESPONSE"

        val RESULT_SUCCESSFUL = -1
    }

    private lateinit var webView: WebView
    private lateinit var errorInfoView: View
    private lateinit var goBackButton: View

    private var webPageLoadingSuccess = true

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threedsecureweb)

        webView = findViewById(R.id.webview)
        errorInfoView = findViewById(R.id.errorInfo)
        goBackButton = findViewById(R.id.button)

        webView.visibility = VISIBLE
        errorInfoView.visibility = GONE

        goBackButton.background = ContextCompat.getDrawable(this, R.drawable.bg_button)
        goBackButton.backgroundTintList = null

        val requestExtra = try {
            intent.extras?.getSerializable(EXTRA_REQUEST) as ThreeDSecureRequest
        } catch (ex: Exception) {
            finishCanceled()
            return
        }

        goBackButton.setOnClickListener {
            finishCanceled()
        }

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                val uri = Uri.parse(url)
                if (url != null && url.startsWith(requestExtra.responseUrl) && uri.queryParameterNames.size > 0) {
                    val response = ThreeDSecureResponse(url)
                    finishSuccess(response)
                }
                showInfoView(ThreeDInfoViewType.InProgress)
            }

            override fun onReceivedError(
                view: WebView?, request: WebResourceRequest?, error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                showInfoView(ThreeDInfoViewType.GeneralError)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dismissInfoView()
            }
        }
        webView.postUrl(requestExtra.url, byteArrayOf())
    }

    private fun finishCanceled() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun finishSuccess(response: ThreeDSecureResponse) {
        setResult(RESULT_SUCCESSFUL, Intent().apply { putExtra(EXTRA_RESPONSE, response) })
        finish()
    }

    private fun showInfoView(infoType: ThreeDInfoViewType) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val button: Button = findViewById(R.id.button)
        val message: TextView = findViewById(R.id.message)
        when (infoType) {
            ThreeDInfoViewType.InProgress -> {
                progressBar.visibility = VISIBLE
                button.visibility = INVISIBLE
                message.text = getText(R.string.tdswa_process_in_progress)
            }
            ThreeDInfoViewType.GeneralError -> {
                progressBar.visibility = GONE
                button.visibility = VISIBLE
                message.text = getText(R.string.tdswa_webViewError_message)
                webPageLoadingSuccess = false
            }
        }
        webView.visibility = GONE
        errorInfoView.visibility = VISIBLE
    }

    private fun dismissInfoView() {
        if (webPageLoadingSuccess) {
            errorInfoView.visibility = GONE
            webView.visibility = VISIBLE
        }
    }
}

internal data class ThreeDSecureRequest(
    val method: String,
    val url: String,
    val headers: HashMap<String, String>,
    val responseUrl: String
) : Serializable

internal data class ThreeDSecureResponse(
    val url: String
) : Serializable

internal enum class ThreeDInfoViewType(val type: Int) {
    InProgress(0),
    GeneralError(1)
}