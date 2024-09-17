package com.vu.finalassignment

import  retrofit2.http.Body
import  retrofit2.http.POST

interface ApiService {
    @POST("/ort/auth") // Endpoint
    suspend fun login(@Body request: LoginRequest): LoginResponse
}