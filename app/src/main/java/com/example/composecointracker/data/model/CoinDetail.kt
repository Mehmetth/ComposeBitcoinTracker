package com.example.composecointracker.data.model

import androidx.compose.runtime.Stable

@Stable
data class CoinDetail(
    var id: String?,
    var symbol: String?,
    var name: String?,
    var block_time_in_minutes: Int?,
    var hashing_algorithm: String?,
    var image: Image?,
    var description: Description?,
    var market_data: MarketData?,
    var genesis_date: String?
)

@Stable
data class Image(
    var large: String?
)

@Stable
data class Description(
    var en: String?
)

@Stable
data class MarketData(
    var current_price: CurrentPrice?,
    var high_24h: High?,
    var low_24h: Low?,
)

@Stable
data class CurrentPrice(
    var usd: String?
)

@Stable
data class High(
    var usd: String?
)

@Stable
data class Low(
    var usd: String?
)