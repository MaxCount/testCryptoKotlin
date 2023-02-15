package by.khomichenko.testcrypto.domain.models.pair

data class PairModel(
    val __type: String,
    val `data`: List<Data>,
    val isLastPage: Boolean
)