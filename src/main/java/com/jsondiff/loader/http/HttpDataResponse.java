package com.jsondiff.loader.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsondiff.loader.DataResponse;

public class HttpDataResponse extends DataResponse {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
