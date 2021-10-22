package com.lucasxvirtual.googlepaypoc.ui

import android.os.Bundle

data class ThreeDSecureWebActivityResult(
    val requestCode: Int,
    val resultCode: Int,
    val bundle: Bundle?
)