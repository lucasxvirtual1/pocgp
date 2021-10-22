package com.lucasxvirtual.googlepaypoc.services.transaction

sealed class ThreeDSecureError {
    object IncorrectVersion: ThreeDSecureError()
    object ChallengeCanceled: ThreeDSecureError()
    object EmptyChallengeResult: ThreeDSecureError()
    object IncorrectTransactionId: ThreeDSecureError()

    object Unknown: ThreeDSecureError()
    data class GeneralError(val errorCode: Int): ThreeDSecureError()
}