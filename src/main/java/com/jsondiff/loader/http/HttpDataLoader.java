package com.jsondiff.loader.http;

import com.jsondiff.loader.DataLoader;
import com.jsondiff.loader.DataRequest;
import com.jsondiff.loader.DataResponse;

public class HttpDataLoader implements DataLoader {
    @Override
    public DataResponse load(DataRequest request) {
        HttpDataRequest httpRequest = (HttpDataRequest) request;
        return null;
    }
}
