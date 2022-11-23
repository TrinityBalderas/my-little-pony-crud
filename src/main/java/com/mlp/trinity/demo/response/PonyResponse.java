package com.mlp.trinity.demo.response;

import com.mlp.trinity.demo.models.PonyModel;

public class PonyResponse {
    public String message;
    public PonyModel body;

    public PonyResponse(PonyModel ponyModel, String message){
        this.message = message;
        this.body = ponyModel;
    }
}
