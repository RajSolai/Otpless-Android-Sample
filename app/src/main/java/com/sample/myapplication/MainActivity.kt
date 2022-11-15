package com.sample.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import com.otpless.main.Otpless
import com.otpless.main.OtplessIntentRequest
import com.otpless.main.OtplessProvider
import com.otpless.main.OtplessTokenData

class MainActivity : AppCompatActivity() {
    private var otpless: Otpless? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        otpless = OtplessProvider.getInstance(this).init(this::onOtplessResult);
    }

    private fun onOtplessResult(@Nullable response: OtplessTokenData?) {
        if (response == null) return
        //Send this token to your backend end api to fetch user details from otpless service
        val token = response.token
        findViewById<TextView>(R.id.txt1).text = token
    }

    private fun initiateOtplessFlow() {

        // Change intentUri
        val intentUri = "whatsapp://send?phone=YOUR_INTENT_URI&text=\\u200E\\u200E\\u200B\\u200C\\u200B\\u200B\\u200B\\u200B\\u200B\\u200C\\u200D\\u200C\\u200B\\u200D\\u200CHi%20WhatsApp!%0APlease%20verify%20my%20number%20with%20Android%20Example%20App.";
        val request = OtplessIntentRequest(intentUri)
            .setLoadingText("Please wait...")
            .setProgressBarColor(R.color.purple_200)
        otpless!!.openOtpless(request)
    }

    fun handleWhatsappButtonClick( v : View){
        initiateOtplessFlow();
    }
}