package com.lucasxvirtual.googlepaypoc.models

sealed class DeviceSafetyWarning {
    object DeviceRooted: DeviceSafetyWarning()
    object SdkIntegrityTampered: DeviceSafetyWarning()
    object EmulatorIsUsed: DeviceSafetyWarning()
    object DebuggerIsAttached: DeviceSafetyWarning()
    object AppInstalledFromUntrustedSource: DeviceSafetyWarning()
}