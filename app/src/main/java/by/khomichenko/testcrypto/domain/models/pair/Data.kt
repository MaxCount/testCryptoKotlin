package by.khomichenko.testcrypto.domain.models.pair

data class Data(
    val __type: String,
    val amountAsset: String,
    val `data`: DataX,
    val priceAsset: String
)