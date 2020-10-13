package com.chess.exceptions;

import com.chess.models.responses.Response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse extends Response {
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

    @Override
    public String toString(){
        return "Message: "  + errMessage + " " + errReason + " Status Code: " + status + " at " + path;
    }

    public int getStatus() {
        return status;
    }

    public String getErrReason() {
        return errReason;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public String getPath() {
        return path;
    }

}
