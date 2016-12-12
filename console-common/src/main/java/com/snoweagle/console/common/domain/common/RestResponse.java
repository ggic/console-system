package com.snoweagle.console.common.domain.common;

import java.io.Serializable;

/**
 * Created by wangshaobo on 2015/10/23.
 */
public class RestResponse implements Serializable {

    private String status;
    private String message;

    private Object content ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
