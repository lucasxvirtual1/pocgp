package com.lucasxvirtual.googlepaypoc

import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.google.android.gms.wallet.WalletConstants
import junit.framework.TestCase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

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
    fun test_googlePay() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay1() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay2() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay3() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay4() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay5() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay6() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay7() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay8() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay9() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay10() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay11() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }@Test
    fun test_googlePay12() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay13() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay14() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay15() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay16() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay17() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay18() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }@Test
    fun test_googlePay19() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay20() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay21() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay22() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay23() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay24() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay25() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay26() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay27() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay28() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay29() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay30() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay31() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay32() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay33() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay34() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay35() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay36() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay37() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
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
    fun test_googlePay39() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay40() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay41() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay42() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay43() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay44() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay45() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay46() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay47() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay48() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay49() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay50() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay51() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay52() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay53() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay54() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay55() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay56() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay57() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay58() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay59() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay60() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay61() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay62() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay63() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay64() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay65() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay66() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay67() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay68() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay69() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay70() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay71() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay72() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay73() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay74() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay75() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay76() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay77() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay78() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay79() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay80() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay81() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay82() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay83() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay84() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay85() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay86() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay87() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay88() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
        TestCase.assertEquals(activity.text.text.contains("api"), true)
    }
    @Test
    fun test_googlePay89() {
        tapPayWithGooglePay()
        tapContinue()
        uiDevice.waitForIdle()
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