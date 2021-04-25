package com.jsondiff.loader;

public class DataResponse {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static final class Builder {
        private Object data;

        private Builder() {
        }

        public static Builder aDataResponse() {
            return new Builder();
        }

        public Builder withData(Object data) {
            this.data = data;
            return this;
        }

        public DataResponse build() {
            DataResponse dataResponse = new DataResponse();
            dataResponse.setData(data);
            return dataResponse;
        }
    }
}
