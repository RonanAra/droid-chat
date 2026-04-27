package br.com.droidchat.data.repository

import br.com.droidchat.model.CreateAccount

interface AuthRepository {
    suspend fun signUp(request: CreateAccount)
    suspend fun signIn(userName: String, password: String)
}
