package com.jsondiff.loader.http;

import com.jsondiff.loader.DataRequest;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Arrays;
import java.util.Optional;

public class HttpDataRequest extends DataRequest {
    enum ContentType {

        APPLICATION_JSON("application/json"),
        APPLICATION_XML("application/xml"),
        APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded");

        private String text;
        public static String CONTENT_TYPE = "Content-Type";

        ContentType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static ContentType findByText(String text) {
            return Arrays.stream(ContentType.values())
                    .filter(contentType -> contentType.getText().equals(text))
                    .findFirst().orElse(null);
        }
    }


    enum HttpMethod {
        GET, PUT, POST, DELETE
    }

    private String url;
    private HttpMethod method;
    private String body;
    private MultiValuedMap<String, String> headers;

    public HttpDataRequest(String url) {
        this.url = url;
        this.method = HttpMethod.GET;
    }

    public String getUrl() {
        return url;
    }

    public HttpDataRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public HttpDataRequest setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HttpDataRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public MultiValuedMap<String, String> getHeaders() {
        return headers;
    }

    public HttpDataRequest setHeaders(MultiValuedMap<String, String> headers) {
        this.headers = headers;
        return this;
    }


    public static final class HttpDataRequestBuilder {
        private String url;
        private HttpMethod method;
        private String body;
        private MultiValuedMap<String, String> headers;

        private HttpDataRequestBuilder() {
        }

        public static HttpDataRequestBuilder aHttpDataRequest() {
            return new HttpDataRequestBuilder();
        }

        public HttpDataRequestBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public HttpDataRequestBuilder withMethod(HttpMethod method) {
            this.method = method;
            return this;
        }

        public HttpDataRequestBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public HttpDataRequestBuilder withHeaders(MultiValuedMap<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpDataRequestBuilder withHeader(String name, String value) {
            this.headers = Optional.ofNullable(headers).orElse(new HashSetValuedHashMap<>());
            headers.put(name, value);
            return this;
        }

        public HttpDataRequest build() {
            HttpDataRequest httpDataRequest = new HttpDataRequest(url);
            httpDataRequest.setMethod(method);
            httpDataRequest.setBody(body);
            httpDataRequest.setHeaders(headers);
            return httpDataRequest;
        }
    }
}
