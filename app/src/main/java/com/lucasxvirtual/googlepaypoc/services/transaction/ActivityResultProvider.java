package com.lucasxvirtual.googlepaypoc.services.transaction;

import com.lucasxvirtual.googlepaypoc.ui.ThreeDSecureWebActivityResult;

public class ActivityResultProvider {
    private ThreeDSecureWebActivityResult result;

    public void setResult(ThreeDSecureWebActivityResult result) {
        this.result = result;
    }

    public ThreeDSecureWebActivityResult getResult() {
        return result;
    }
}
