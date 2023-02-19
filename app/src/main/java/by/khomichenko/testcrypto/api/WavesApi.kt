package by.khomichenko.testcrypto.api

import by.khomichenko.testcrypto.domain.models.pair.PairModel
import retrofit2.Response
import retrofit2.http.GET

interface WavesApi {

    @GET(value = "v0/pairs?search_by_assets=btc%2C%20usdt")
   suspend fun getExchangeRange(): Response<PairModel>
}