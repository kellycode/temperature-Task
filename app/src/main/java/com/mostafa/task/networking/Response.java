package com.mostafa.task.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */


public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }
}
