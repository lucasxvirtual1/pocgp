package com.lucasxvirtual.googlepaypoc.ui

import android.content.Context
import android.content.res.Configuration
import com.cardinalcommerce.cardinalmobilesdk.models.CardinalConfigurationParameters
import com.cardinalcommerce.shared.models.enums.ButtonType
import com.cardinalcommerce.shared.userinterfaces.*
import com.lucasxvirtual.googlepaypoc.R

data class CardinalStyleManager(
        val verifyButtonStyle: ButtonStyle? = null,
        val resendButtonStyle: ButtonStyle? = null,
        val toolbarStyle: ToolbarStyle? = null,
        val textBoxStyle: TextBoxStyle? = null,
        val labelStyle: LabelStyle? = null
) {

    data class ButtonStyle(
        val textColor: String? = null,
        val backgroundColor: String? = null,
        val textFontName: String? = null,
        val cornerRadius: Int? = null,
        val textFontSize: Int? = null
    )

    data class ToolbarStyle(
        val backgroundColor: String? = null,
        val buttonText: String? = null,
        val headerText: String? = null,
        val textFontName: String? = null,
        val textFontSize: Int? = null,
        val cancelButtonTextColor: String? = null,
        val cancelButtonBackgroundColor: String? = null,
        val cancelButtonTextFontName: String? = null,
        val cancelButtonCornerRadius: Int? = null,
        val cancelButtonTextFontSize: Int? = null
    )

    data class TextBoxStyle(
        val borderColor: String? = null,
        val borderWidth: Int? = null,
        val cornerRadius: Int? = null,
        val textColor: String? = null,
        val textFontName: String? = null,
        val textFontSize: Int? = null
    )

    data class LabelStyle(
        val headingTextColor: String? = null,
        val headingTextFontName: String? = null,
        val headingTextFontSize: Int? = null,
        val textColor: String? = null,
        val textFontName: String? = null,
        val textFontSize: Int? = null
    )

    companion object {
        @JvmSynthetic
        internal fun isDarkThemeSetOnDevice(context: Context): Boolean {
            return context.resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        }

        private fun getDefaultConfigForDarkTheme(context: Context): UiCustomization {
            val uiCustomization = UiCustomization()

            val verifyButton = ButtonCustomization()
            verifyButton.textColor = "#FFFFFF"
            verifyButton.backgroundColor = "#BB86FC"
            uiCustomization.setButtonCustomization(verifyButton, ButtonType.VERIFY)

            val resendButton = ButtonCustomization()
            resendButton.textColor = "#BB86FC"
            uiCustomization.setButtonCustomization(resendButton, ButtonType.RESEND)

            val toolbarCustomization = ToolbarCustomization()
            val cancelButton = ButtonCustomization()
            toolbarCustomization.backgroundColor = "#121212"
            toolbarCustomization.buttonText = context.getString(R.string.tds_challenge_popup_cancel_button)
            toolbarCustomization.headerText = context.getString(R.string.tds_challenge_popup_title)
            toolbarCustomization.textColor = "#FFFFFF"
            cancelButton.textColor = "#FFFFFF"
            uiCustomization.toolbarCustomization = toolbarCustomization
            uiCustomization.setButtonCustomization(cancelButton, ButtonType.CANCEL)

            val textBoxCustomization = TextBoxCustomization()
            textBoxCustomization.borderColor = "#BB86FC"
            textBoxCustomization.borderWidth = 2
            uiCustomization.textBoxCustomization = textBoxCustomization

            return uiCustomization
        }

        private fun getDefaultConfigForLightTheme(context: Context): UiCustomization {
            val uiCustomization = UiCustomization()

            val toolbarCustomization = ToolbarCustomization()
            toolbarCustomization.buttonText = context.getString(R.string.tds_challenge_popup_cancel_button)
            toolbarCustomization.headerText = context.getString(R.string.tds_challenge_popup_title)
            uiCustomization.toolbarCustomization = toolbarCustomization

            return uiCustomization
        }

        @JvmSynthetic
        internal fun applyDarkTheme(
                context: Context,
                cardinalStyleManager: CardinalStyleManager?,
                cardinalConfigurationParameters: CardinalConfigurationParameters
        ) {
            cardinalStyleManager?.let {
                cardinalConfigurationParameters.uiCustomization = it.getUiCustomization(context)
                it.applyDarkThemeForMissingAttrs(
                    context,
                    cardinalConfigurationParameters.uiCustomization
                )
            } ?: run {
                cardinalConfigurationParameters.uiCustomization = getDefaultConfigForDarkTheme(context)
            }
        }

        @JvmSynthetic
        internal fun applyLightTheme(
                context: Context,
                cardinalStyleManager: CardinalStyleManager?,
                cardinalConfigurationParameters: CardinalConfigurationParameters
        ) {
            cardinalStyleManager?.let {
                cardinalConfigurationParameters.uiCustomization = cardinalStyleManager.getUiCustomization(context)
            } ?: run {
                cardinalConfigurationParameters.uiCustomization = getDefaultConfigForLightTheme(context)
            }
        }
    }

    private fun getUiCustomization(applicationContext: Context): UiCustomization {
        val uiCustomization = UiCustomization()

        verifyButtonStyle?.let { buttonStyle ->
            val verifyButton = ButtonCustomization()

            buttonStyle.textColor?.let {
                verifyButton.textColor = it
            }
            buttonStyle.backgroundColor?.let {
                verifyButton.backgroundColor = it
            }
            buttonStyle.textFontName?.let {
                verifyButton.textFontName = it
            }
            buttonStyle.cornerRadius?.let {
                verifyButton.cornerRadius = it
            }
            buttonStyle.textFontSize?.let {
                verifyButton.textFontSize = it
            }

            uiCustomization.setButtonCustomization(verifyButton, ButtonType.VERIFY)
        }

        resendButtonStyle?.let { buttonStyle ->
            val resendButton = ButtonCustomization()

            buttonStyle.textColor?.let {
                resendButton.textColor = it
            }
            buttonStyle.backgroundColor?.let {
                resendButton.backgroundColor = it
            }
            buttonStyle.textFontName?.let {
                resendButton.textFontName = it
            }
            buttonStyle.cornerRadius?.let {
                resendButton.cornerRadius = it
            }
            buttonStyle.textFontSize?.let {
                resendButton.textFontSize = it
            }

            uiCustomization.setButtonCustomization(resendButton, ButtonType.RESEND)
        }

        uiCustomization.toolbarCustomization = toolbarStyle?.let { toolbarStyle ->
            val toolbarCustomization = ToolbarCustomization()
            val cancelButton = ButtonCustomization()

            toolbarStyle.backgroundColor?.let {
                toolbarCustomization.backgroundColor = it
            }

            toolbarCustomization.buttonText = toolbarStyle.buttonText ?: applicationContext.getString(R.string.tds_challenge_popup_cancel_button)
            toolbarCustomization.headerText = toolbarStyle.headerText ?: applicationContext.getString(R.string.tds_challenge_popup_title)

            toolbarStyle.textFontName?.let {
                toolbarCustomization.textFontName = it
            }
            toolbarStyle.textFontSize?.let {
                toolbarCustomization.textFontSize = it
            }
            toolbarStyle.cancelButtonTextColor?.let {
                cancelButton.textColor = it
            }
            toolbarStyle.cancelButtonBackgroundColor?.let {
                cancelButton.backgroundColor = it
            }
            toolbarStyle.cancelButtonTextFontName?.let {
                cancelButton.textFontName = it
            }
            toolbarStyle.cancelButtonCornerRadius?.let {
                cancelButton.cornerRadius = it
            }
            toolbarStyle.cancelButtonTextFontSize?.let {
                cancelButton.textFontSize = it
            }

            uiCustomization.setButtonCustomization(cancelButton, ButtonType.CANCEL)
            toolbarCustomization
        } ?: ToolbarCustomization().apply {
            this.buttonText = applicationContext.getString(R.string.tds_challenge_popup_cancel_button)
            this.headerText = applicationContext.getString(R.string.tds_challenge_popup_title)
        }

        textBoxStyle?.let { textBoxStyle ->
            val textBoxCustomization = TextBoxCustomization()

            textBoxStyle.borderColor?.let {
                textBoxCustomization.borderColor = it
            }
            textBoxStyle.borderWidth?.let {
                textBoxCustomization.borderWidth = it
            }
            textBoxStyle.cornerRadius?.let {
                textBoxCustomization.cornerRadius = it
            }
            textBoxStyle.textColor?.let {
                textBoxCustomization.textColor = it
            }
            textBoxStyle.textFontName?.let {
                textBoxCustomization.textFontName = it
            }
            textBoxStyle.textFontSize?.let {
                textBoxCustomization.textFontSize = it
            }

            uiCustomization.textBoxCustomization = textBoxCustomization
        }

        labelStyle?.let { labelStyle ->
            val labelCustomization = LabelCustomization()

            labelStyle.headingTextColor?.let {
                labelCustomization.headingTextColor = it
            }
            labelStyle.headingTextFontName?.let {
                labelCustomization.headingTextFontName = it
            }
            labelStyle.headingTextFontSize?.let {
                labelCustomization.headingTextFontSize = it
            }
            labelStyle.textColor?.let {
                labelCustomization.textColor = it
            }
            labelStyle.textFontName?.let {
                labelCustomization.textFontName = it
            }
            labelStyle.textFontSize?.let {
                labelCustomization.textFontSize = it
            }

            uiCustomization.labelCustomization = labelCustomization
        }

        return uiCustomization
    }

    /**
     * Used for init the missing [UiCustomization] attributes with the default dark theme values in
     * case of using a custom [CardinalStyleManager] by a merchant.
     */
    private fun applyDarkThemeForMissingAttrs(
        context: Context,
        uiCustomization: UiCustomization
    ): UiCustomization {

        val defaultCustomization = getDefaultConfigForDarkTheme(context)

        if (uiCustomization.getButtonCustomization(ButtonType.VERIFY) == null) {
            val verifyButton = ButtonCustomization()
            verifyButton.textColor =
                defaultCustomization.getButtonCustomization(ButtonType.VERIFY).textColor
            verifyButton.backgroundColor =
                defaultCustomization.getButtonCustomization(ButtonType.VERIFY).backgroundColor
            uiCustomization.setButtonCustomization(verifyButton, ButtonType.VERIFY)
        }

        if (uiCustomization.getButtonCustomization(ButtonType.RESEND) == null) {
            val resendButton = ButtonCustomization()
            resendButton.textColor =
                defaultCustomization.getButtonCustomization(ButtonType.RESEND).textColor
            uiCustomization.setButtonCustomization(resendButton, ButtonType.RESEND)
        }

        if (uiCustomization.toolbarCustomization == null) {
            val toolbarCustomization = ToolbarCustomization()
            val cancelButton = ButtonCustomization()
            toolbarCustomization.backgroundColor =
                defaultCustomization.toolbarCustomization.backgroundColor
            toolbarCustomization.buttonText = defaultCustomization.toolbarCustomization.buttonText
            toolbarCustomization.headerText = defaultCustomization.toolbarCustomization.headerText
            toolbarCustomization.textColor = defaultCustomization.toolbarCustomization.textColor
            cancelButton.textColor =
                defaultCustomization.getButtonCustomization(ButtonType.CANCEL).textColor
            uiCustomization.toolbarCustomization = toolbarCustomization
            uiCustomization.setButtonCustomization(cancelButton, ButtonType.CANCEL)
        }

        if (uiCustomization.textBoxCustomization == null) {
            val textBoxCustomization = TextBoxCustomization()
            textBoxCustomization.borderColor = defaultCustomization.textBoxCustomization.borderColor
            textBoxCustomization.borderWidth = defaultCustomization.textBoxCustomization.borderWidth
            uiCustomization.textBoxCustomization = textBoxCustomization

        }

        return uiCustomization
    }
}