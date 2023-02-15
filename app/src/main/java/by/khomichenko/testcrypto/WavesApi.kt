package by.khomichenko.testcrypto

import by.khomichenko.testcrypto.domain.models.pair.PairModel
import retrofit2.Call
import retrofit2.http.GET

interface WavesApi {

    @GET(value = "v0/pairs?search_by_assets=btc%2C%20usdt")
    fun getExchangeRange(): Call<PairModel>
}