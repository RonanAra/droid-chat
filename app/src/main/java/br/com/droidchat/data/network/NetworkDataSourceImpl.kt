package br.com.droidchat.data.network

import br.com.droidchat.data.network.model.AuthRequest
import br.com.droidchat.data.network.model.CreateAccountRequest
import br.com.droidchat.data.network.model.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient

) : NetworkDataSource {

    override suspend fun signUp(request: CreateAccountRequest) {
        httpClient
            .post(urlString = "signup") { setBody(request) }
            .body<Unit>()
    }

    override suspend fun signIn(
        request: AuthRequest
    ): TokenResponse = httpClient
        .post(urlString = "signin") { setBody(request) }
        .body()
}