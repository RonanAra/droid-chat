package br.com.droidchat.data.repository

import br.com.droidchat.data.network.NetworkDataSource
import br.com.droidchat.data.network.model.AuthRequest
import br.com.droidchat.data.network.model.CreateAccountRequest
import br.com.droidchat.model.CreateAccount
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : AuthRepository {

    override suspend fun signUp(request: CreateAccount) {
        return networkDataSource.signUp(
            request = CreateAccountRequest(
                username = request.username,
                password = request.password,
                firstName = request.firstName,
                lastName = request.lastName,
                profilePictureId = request.profilePictureId
            )
        )
    }

    override suspend fun signIn(userName: String, password: String) {
        networkDataSource.signIn(
            request = AuthRequest(
                username = userName,
                password = password
            )
        )
    }
}