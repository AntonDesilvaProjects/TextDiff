package com.jsondiff.loader.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsondiff.loader.AbstractDataLoader;
import com.jsondiff.loader.DataRequest;
import com.jsondiff.loader.DataResponse;
import okhttp3.*;
import org.apache.commons.collections4.MultiValuedMap;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class HttpJsonDataLoader extends AbstractDataLoader {

    private final OkHttpClient httpClient = new OkHttpClient();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse(HttpDataRequest.ContentType.APPLICATION_JSON.getText());

    @Override
    public DataResponse load(DataRequest request) {
        HttpDataRequest httpRequest = getDataRequestAs(request, HttpDataRequest.class);
        JsonNode jsonResponse = executeAndGetResponse(httpRequest);
        return DataResponse.Builder.aDataResponse().withData(jsonResponse).build();
    }

    private JsonNode executeAndGetResponse(HttpDataRequest httpDataRequest) {
        Request okHttpRequest = buildOkHttpRequest(httpDataRequest);
        try {
            Response response = httpClient.newCall(okHttpRequest).execute();
            String rawResponse = response.body().string();
            return readAsJson(rawResponse);
        } catch (IOException jsonException) {
            throw new RuntimeException(jsonException.getLocalizedMessage());
        }
    }

    private Request buildOkHttpRequest(HttpDataRequest request) {
        Request.Builder builder = new Request.Builder()
                .url(request.getUrl());
        if (HttpDataRequest.HttpMethod.GET.equals(request.getMethod())) {
            builder.get();
        } else {
            builder.method(request.getMethod().name(), RequestBody.create(request.getBody(), getMediaType(request)));
        }
        builder.headers(getHeaders(request.getHeaders()));

        return builder.build();
    }

    private MediaType getMediaType(HttpDataRequest request) {
        // check the content type header to figure out the MediaType
        // if not available, assume it JSON
        return Optional.ofNullable(request.getHeaders())
                .map(headerMap -> headerMap.get(HttpDataRequest.ContentType.CONTENT_TYPE))
                .filter(contentTypeHeaders -> !contentTypeHeaders.isEmpty())
                .map(contentTypeHeader -> contentTypeHeader.iterator().next())
                .map(MediaType::parse)
                .orElse(JSON_MEDIA_TYPE);
    }

    private Headers getHeaders(MultiValuedMap<String, String> headerMap) {
        final Headers.Builder builder = new Headers.Builder();
        Optional.ofNullable(headerMap)
                .map(MultiValuedMap::keySet)
                .orElse(Set.of())
                .stream()
                .forEach(headerName -> headerMap.get(headerName).stream()
                        .forEach(headerValue -> builder.add(headerName, headerValue)));
        return builder.build();
    }
}
