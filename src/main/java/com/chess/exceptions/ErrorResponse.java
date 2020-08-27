package com.chess.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {
    private int status;
    private String errReason;
    private String errMessage;
    private String path;
    //public static List<ErrorResponse> = new ArrayList<ErrorResponse>();


    public ErrorResponse(int status, String errReason, String errMessage, String path) {
        this.status = status;
        this.errReason = errReason;
        this.errMessage = errMessage;
        this.path = path;
    }

    @Override
    public String toString(){
        return "Status Code: "  + status + " " + errReason + " Message: " + errMessage + " at " + path;
    }
}
