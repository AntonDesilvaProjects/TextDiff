package com.jsondiff.loader.http;

import com.jsondiff.loader.DataLoader;
import com.jsondiff.loader.DataRequest;
import com.jsondiff.loader.DataResponse;
import okhttp3.*;
import org.apache.commons.collections4.MultiValuedMap;

import java.io.IOException;
import java.util.Collection;

public class HttpDataLoader implements DataLoader {

    final OkHttpClient httpClient = new OkHttpClient();

    @Override
    public DataResponse load(DataRequest request) {
        HttpDataRequest httpRequest = (HttpDataRequest) request;
        Request okHttpRequest = buildOkHttpRequest(httpRequest);
        try {
            Response response = httpClient.newCall(okHttpRequest).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Request buildOkHttpRequest(HttpDataRequest request) {
        return new Request.Builder()
                .url(request.getUrl())
                .method(request.getMethod().name(), RequestBody.create(request.getBody(), getMediaType(request.getHeaders())))
                .headers(getHeaders(request.getHeaders()))
                .build();
    }

    private MediaType getMediaType(MultiValuedMap<String, String> headers) {
        // check the content type header to figure out the MediaType
        // if not available, assume it JSON
       return null;
    }

    private Headers getHeaders(MultiValuedMap<String, String> headerMap) {
       final Headers.Builder builder = new Headers.Builder();
       for (String headerName : headerMap.keySet()) {
           Collection<String> headers = headerMap.get(headerName);
           for (String header : headers) {
               builder.add(headerName, header);
           }
       }
       return builder.build();
    }
}
