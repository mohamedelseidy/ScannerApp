package com.asha.playerlist

data class AccessToken(
    val access_token: String,
    val expires_in: Int,
    val ext_expires_in: Int,
    val token_type: String
)