package com.lucasxvirtual.googlepaypoc

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.lucasxvirtual.googlepaypoc.models.api.response.RequestType
import com.lucasxvirtual.googlepaypoc.models.api.response.ResponseErrorCode
import com.lucasxvirtual.googlepaypoc.services.api.TrustPaymentsGatewayType
import com.lucasxvirtual.googlepaypoc.services.transaction.PaymentTransactionManager
import com.lucasxvirtual.googlepaypoc.util.ResponseParser
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android uiDevice.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var activity: CheckoutActivity

    @get:Rule
    val initialActivityTestRule = ActivityTestRule(CheckoutActivity::class.java)

    private lateinit var displayMetrics : DisplayMetrics

    val uiDevice: UiDevice by lazy {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Before
    fun setUp() {
        activity = initialActivityTestRule.activity
        displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val display = activity.display
            display?.getRealMetrics(displayMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = activity.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(displayMetrics)
        }
    }

    @Test
    fun test_googlePay() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay1() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay2() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay3() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay4() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay5() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay6() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay7() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay8() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay9() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay10() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay11() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay12() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay13() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay14() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay15() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay16() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay17() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay18() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay19() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay20() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay21() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay22() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay23() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay24() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay25() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay26() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay27() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay28() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay29() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay30() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay31() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay32() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay33() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay34() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay35() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay36() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay37() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay38() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay39() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay40() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay41() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay42() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay43() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay44() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay45() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay46() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay47() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay48() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay49() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay50() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay51() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay52() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay53() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay54() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay55() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay56() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay57() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay58() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay59() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay60() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay61() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay62() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay63() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay64() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay65() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay66() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay67() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay68() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay69() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay70() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay71() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay72() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay73() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay74() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay75() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay76() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay77() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay78() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay79() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay80() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay81() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay82() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay83() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay84() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay85() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay86() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay87() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay88() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay89() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay90() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay91() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay92() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay93() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay94() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay95() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay96() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay97() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay98() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay99() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay100() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Context>().applicationContext
        val jwtBuilder = JWTBuilder("MERCHANT_USERNAME",
            "SITE_REFERENCE",
            "KEY")
        val paymentTransactionManager = PaymentTransactionManager(
            app,
            TrustPaymentsGatewayType.EU,
            false,
            "MERCHANT_USERNAME"
        )
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        val text = activity.text.text?.toString()

        TestCase.assertNotNull(text)

        val token = jwtBuilder.getForGooglePay(walletToken = text!!, requestTypes = listOf(
            RequestType.Auth))

        val session = paymentTransactionManager.createSession({token})
        val result = paymentTransactionManager.executeSession(session)
        val parsedResponse = ResponseParser.parse(result.responseJwtList.first())

        //THEN
        TestCase.assertNotNull(parsedResponse)
        TestCase.assertEquals(1, parsedResponse!!.responses.size)
        TestCase.assertEquals("Ok", parsedResponse.responses[0].errorMessage)
        assertEquals(
            ResponseErrorCode.Ok,
            parsedResponse.responses[0].errorCode
        )
        TestCase.assertNotNull(parsedResponse.responses[0].requestTypeDescription)
        TestCase.assertEquals(
            "AUTH",
            parsedResponse.responses[0].requestTypeDescription!!.serializedName
        )
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }


    fun tapPayWithGooglePay(){
        uiDevice.findObject(
            UiSelector().className(RelativeLayout::class.java)
        ).clickAndWaitForNewWindow()
    }

    fun tapContinue(){
        uiDevice.wait(Until.findObject(By.clazz(Button::class.java)), 3000L)
        if(uiDevice.hasObject(By.clazz(Button::class.java))){
            uiDevice.findObject(UiSelector().className(Button::class.java)).click()
        } else {
            // guessing
            uiDevice.click(displayMetrics.widthPixels / 2,
                (displayMetrics.heightPixels - displayMetrics.heightPixels * 0.06).toInt()
            )
            uiDevice.click( (displayMetrics.widthPixels - displayMetrics.widthPixels * 0.1).toInt(),
                (displayMetrics.heightPixels - displayMetrics.heightPixels * 0.06).toInt()
            )
            uiDevice.click( (displayMetrics.widthPixels - displayMetrics.widthPixels * 0.1).toInt(),
                (displayMetrics.heightPixels - displayMetrics.heightPixels * 0.01).toInt()
            )
        }
        uiDevice.wait(Until.findObject(By.clazz(EditText::class.java.name)), 3000L)
        if(uiDevice.hasObject(By.clazz(EditText::class.java.name))){
            uiDevice.findObject(By.clazz(EditText::class.java.name)).text = "2525"
            uiDevice.pressEnter()
            uiDevice.waitForIdle()
        }
    }

}