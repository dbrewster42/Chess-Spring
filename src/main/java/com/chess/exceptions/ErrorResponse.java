package com.chess.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {
    private int status;
    private String errReason;
    private String errMessage;
    private String path;

    public ErrorResponse(int status, String errReason, String errMessage, String path) {
        this.status = status;
        this.errReason = errReason;
        this.errMessage = errMessage;
        this.path = path;
    }

    public ErrorResponse(int status, String errMessage){
        this.status = status;
        this.errMessage = errMessage;
    }

    @Override
    public String toString(){
        return "Status Code: "  + status + " " + errReason + " Message: " + errMessage + " at " + path;
    }
}
