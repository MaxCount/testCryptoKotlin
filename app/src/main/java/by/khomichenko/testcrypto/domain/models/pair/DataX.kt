package by.khomichenko.testcrypto.domain.models.pair

data class DataX(
    val firstPrice: Int,
    val high: Double,
    val lastPrice: Double,
    val low: Double,
    val quoteVolume: Double,
    val txsCount: Int,
    val volume: Double,
    val volumeWaves: Double,
    val weightedAveragePrice: Double
)