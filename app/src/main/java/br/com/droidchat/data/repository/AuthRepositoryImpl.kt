package br.com.droidchat.data.repository

import br.com.droidchat.data.network.NetworkDataSource
import br.com.droidchat.data.network.model.AuthRequest
import br.com.droidchat.data.network.model.CreateAccountRequest
import br.com.droidchat.model.CreateAccount
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : AuthRepository {

    override suspend fun signUp(account: CreateAccount) {
        return networkDataSource.signUp(
            request = CreateAccountRequest(
                username = account.username,
                password = account.password,
                firstName = account.firstName,
                lastName = account.lastName,
                profilePictureId = account.profilePictureId
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