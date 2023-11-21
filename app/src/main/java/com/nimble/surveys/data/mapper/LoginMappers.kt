package com.nimble.surveys.data.mapper


import com.nimble.surveys.data.api.model.LoginRequest
import com.nimble.surveys.data.api.model.LoginResponse
import com.nimble.surveys.domain.model.LoginRequestUI
import com.nimble.surveys.domain.model.LoginResponseUI
import com.nimble.surveys.extentions.encrypt

fun LoginResponse.toLoginResponseUI(): LoginResponseUI {
    with(data.attributes){
        return LoginResponseUI(
            accessToken=accessToken.encrypt(),
            createdAt=createdAt,
            expiresIn=expiresIn,
            refreshToken=refreshToken.encrypt(),
            tokenType=tokenType
        )
    }
}

fun LoginRequestUI.toLoginRequest(): LoginRequest {
    return LoginRequest(
        email=email,
        password=password
    )
}
