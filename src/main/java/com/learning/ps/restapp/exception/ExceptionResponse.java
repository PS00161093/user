package com.learning.ps.restapp.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timeStamp;
    private String errMsg;
    private String trace;

    public ExceptionResponse(Date timeStamp, String errMsg, String trace) {
        super();
        this.timeStamp = timeStamp;
        this.errMsg = errMsg;
        this.trace = trace;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
