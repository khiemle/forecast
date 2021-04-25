package com.khiemle.data.deps

data class AppConfigs(
    val baseUrl: String,
    val appId: String,
    val openWeatherDomain: String,
    val pinCertSHA: String,
    val pinCertSHABackupA: String,
    val pinCertSHABackupB: String
)