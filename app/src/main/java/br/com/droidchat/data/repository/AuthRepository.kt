package br.com.droidchat.data.repository

import br.com.droidchat.model.CreateAccount

interface AuthRepository {
    suspend fun signUp(account: CreateAccount)
    suspend fun signIn(userName: String, password: String)
}
