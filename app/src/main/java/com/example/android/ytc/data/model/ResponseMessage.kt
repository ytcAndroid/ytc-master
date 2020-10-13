package com.example.android.ytc.data.model

import com.google.gson.annotations.SerializedName

/**
 * {"message":"Hello Bob! Welcome to mock server."}
 */
class ResponseMessage(
    @SerializedName("message")
    var messsage: String? = null
)
