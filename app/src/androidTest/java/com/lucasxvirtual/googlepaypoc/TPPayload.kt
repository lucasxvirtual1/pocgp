package com.lucasxvirtual.googlepaypoc

import com.google.gson.annotations.SerializedName


open class BasePayload constructor(
    private val requesttypedescriptions: List<String>,
    val termurl: String
)

abstract class TPPayload : BasePayload {
    val accountTypeDescription: String

    val siteReference: String

    val currencyISO3a: String?
    val baseAmount: String?
    val mainAmount: String?

    val parentTransactionReference: String?
    val credentialsOnFile: CredentialsOnFile?
    val threeDBypassPaymentTypes: List<String>?
    val locale: String?

    val billingprefixname: String?
    val billingfirstname: String?
    val billingmiddlename: String?
    val billinglastname: String?
    val billingsuffixname: String?
    val billingstreet: String?
    val billingtown: String?
    val billingcounty: String?
    val billingcountryiso2a: String?
    val billingpostcode: String?
    val billingemail: String?
    val billingtelephone: String?
    val customerprefixname: String?
    val customerfirstname: String?
    val customermiddlename: String?
    val customerlastname: String?
    val customersuffixname: String?
    val customerstreet: String?
    val customertown: String?
    val customercounty: String?
    val customercountryiso2a: String?
    val customerpostcode: String?
    val customeremail: String?
    val customertelephone: String?

    val walletSource : String?
    val walletToken : String?

    val billingContactDetailsOverride : Int?
    val customerContactDetailsOverride : Int?

    constructor(
        accountTypeDescription: String,
        siteReference: String,
        requestTypeDescriptions: List<String>,
        currencyISO3a: String?,
        baseAmount: Int?,
        parentTransactionReference: String? = null,
        credentialsOnFile: CredentialsOnFile? = null,
        locale: String? = null,
        threeDBypassCards: List<String>? = null,
        mainAmount: String? = null,
        billingprefixname: String? = null,
        billingfirstname: String? = null,
        billingmiddlename: String? = null,
        billinglastname: String? = null,
        billingsuffixname: String? = null,
        billingstreet: String? = null,
        billingtown: String? = null,
        billingcounty: String? = null,
        billingcountryiso2a: String? = null,
        billingpostcode: String? = null,
        billingemail: String? = null,
        billingtelephone: String? = null,
        customerprefixname: String? = null,
        customerfirstname: String? = null,
        customermiddlename: String? = null,
        customerlastname: String? = null,
        customersuffixname: String? = null,
        customerstreet: String? = null,
        customertown: String? = null,
        customercounty: String? = null,
        customercountryiso2a: String? = null,
        customerpostcode: String? = null,
        customeremail: String? = null,
        customertelephone: String? = null,
        walletSource: String? = null,
        walletToken: String? = null,
        billingContactDetailsOverride: Int? = null,
        customerContactDetailsOverride: Int? = null
    ) : super(requestTypeDescriptions, "https://payments.securetrading.net/process/payments/mobilesdklistener") {
        this.accountTypeDescription = accountTypeDescription
        this.siteReference = siteReference
        this.currencyISO3a = currencyISO3a
        this.baseAmount = baseAmount?.toString()
        this.parentTransactionReference = parentTransactionReference
        this.credentialsOnFile = credentialsOnFile
        this.locale = locale
        this.threeDBypassPaymentTypes = threeDBypassCards
        this.mainAmount = mainAmount

        this.billingprefixname = billingprefixname
        this.billingfirstname = billingfirstname
        this.billingmiddlename = billingmiddlename
        this.billinglastname = billinglastname
        this.billingsuffixname = billingsuffixname
        this.billingstreet = billingstreet
        this.billingtown = billingtown
        this.billingcounty = billingcounty
        this.billingcountryiso2a = billingcountryiso2a
        this.billingpostcode = billingpostcode
        this.billingemail = billingemail
        this.billingtelephone = billingtelephone
        this.customerprefixname = customerprefixname
        this.customerfirstname = customerfirstname
        this.customermiddlename = customermiddlename
        this.customerlastname = customerlastname
        this.customersuffixname = customersuffixname
        this.customerstreet = customerstreet
        this.customertown = customertown
        this.customercounty = customercounty
        this.customercountryiso2a = customercountryiso2a
        this.customerpostcode = customerpostcode
        this.customeremail = customeremail
        this.customertelephone = customertelephone

        this.walletSource = walletSource
        this.walletToken = walletToken
        this.billingContactDetailsOverride = billingContactDetailsOverride
        this.customerContactDetailsOverride = customerContactDetailsOverride
    }
}

class StandardTPPayload: TPPayload {
    constructor(
        siteReference: String,
        currencyISO3a: String,
        baseAmount: Int,
        requestTypeDescriptions: List<String>,
        credentialsOnFile: CredentialsOnFile? = null,
        parentTransactionReference: String? = null,
        locale: String? = null,
        threeDBypassCards: List<String>? = null

    ) :
            super(
                "ECOM",
                siteReference,
                requestTypeDescriptions,
                currencyISO3a,
                baseAmount,
                parentTransactionReference,
                credentialsOnFile,
                locale,
                threeDBypassCards
            )
}

class GooglePayTPPayload: TPPayload {

    constructor(
            siteReference: String,
            currencyISO3a: String,
            baseAmount: Int,
            requestTypeDescriptions: List<String>,
            walletToken: String,
            walletSource: String = "GOOGLEPAY",
            billingContactDetailsOverride: Int = 0,
            customerContactDetailsOverride: Int = 0,
            billingEmail: String? = null,
            billingFirstName: String? = null,
            billingPostCode: String? = null,
            customerEmail: String? = null,
            customerFirstName: String? = null,
            customerPostCode: String? = null,
            credentialsOnFile: CredentialsOnFile? = null,
            parentTransactionReference: String? = null,
            locale: String? = null,
            threeDBypassCards: List<String>? = null
    ):
            super(
                    "ECOM",
                    siteReference,
                    requestTypeDescriptions,
                    currencyISO3a,
                    baseAmount,
                    parentTransactionReference,
                    credentialsOnFile,
                    locale,
                    threeDBypassCards,
                    walletToken = walletToken,
                    walletSource = walletSource,
                    billingContactDetailsOverride = billingContactDetailsOverride,
                    customerContactDetailsOverride = customerContactDetailsOverride,
                    billingemail = billingEmail,
                    customeremail = customerEmail,
                    billingfirstname = billingFirstName,
                    customerfirstname = customerFirstName,
                    billingpostcode = billingPostCode,
                    customerpostcode = customerPostCode
            )
}

enum class CredentialsOnFile {
    @SerializedName("1")
    SaveForFutureUse,

    @SerializedName("2")
    UsePreviouslySaved
}

enum class SubscriptionUnit {
    @SerializedName("DAY")
    Day,

    @SerializedName("MONTH")
    Month
}

enum class SubscriptionType {
    @SerializedName("RECURRING")
    Recurring,

    @SerializedName("INSTALLMENT")
    Installment
}