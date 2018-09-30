package com.jqmkj.java.web.rest.errors;

import com.jqmkj.java.util.domain.Parameter;

import java.util.Map;
/**
 * @author wyy
 */
public class CustomParameterizedException extends RuntimeException {


    private Map<String, Object> params;

    public CustomParameterizedException(String error, String... params) {
        super(error);
        this.params = new Parameter(params);

    }

    public CustomParameterizedException(String error, Map<String, Object> params) {
        super(error);
        this.params = params;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
